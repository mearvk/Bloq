package apml.system;

import apml.drivers.Stdbloqdriver;
import apml.drivers.Stddriver;
import apml.helpers.Fileloader;
import apml.modeling.Apmllistener;
import apml.modeling.Apmlobject;
import apml.modeling.Apmlsubscriber;
import apml.modeling.Apmlsystem;
import apml.system.bodi.Bodi;
import apml.system.functions.DisplayMessage;
import apml.system.work.Work;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Apmlbasesystem implements apml.interfaces.BasicSystemElement
{   
    public final Integer hash = 0x00888FE8;

	//
	public static Map map = null;

	//
	public ApmlKernelThread runner;

	//
	public ArrayList<Apmlsystem> systems = new ArrayList();

	//
	public static ArrayList<Apmlsystem> _systems = new ArrayList();

	//
	public ArrayList<Apmlobject> objects = new ArrayList();

	//
	public static ArrayList<Apmlobject> _objects = new ArrayList();

	//
	public ArrayList<Apmllistener> listeners = new ArrayList();

	//
	public static ArrayList<Apmllistener> _listeners = new ArrayList();

	//
	public ArrayList<Apmlsubscriber> subscribers = new ArrayList();

	//
	public static ArrayList<Apmlsubscriber> _subscribers = new ArrayList();

	//
	public Map properties = new HashMap<Object,Object>();

	//
	public Stddriver driver;

	//
	public String apmlfile;

	//
	public String basedir;

	//
	public ArrayList<Class> classes = new ArrayList();

	//
	public ArrayList<String> classnames = new ArrayList();


	/**
	 *
	 * @param apmlfile
	 * @param basedir
	 * @param driver
	 */
	public Apmlbasesystem(String apmlfile, String basedir, Stddriver driver)
    {
        this.setapmlfile(apmlfile);

        this.setbasedir(basedir);

		this.setdriver(driver);
	}

	/**
	 * @param apmlfile
	 * @param basedir
	 */
	public Apmlbasesystem(String apmlfile, String basedir)
	{
		this.setbasedir(basedir);
	}


	/**
	 * @param driver
	 */
	public Apmlbasesystem(Stddriver driver)
	{
		this.setdriver(driver);
	}

	public static void main(String... args)
	{
		Apmlbasesystem apml = new Apmlbasesystem("/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development/in/xml/system_test_001.xml", "/Users/mrupplin/IdeaProjects/bloq/maxrupplin/development", new Stdbloqdriver());

		//
		apml.init();

		//
		apml.start();

		//
		apml.run();

		//
		Work work = new Work("Work Unit #001");

		work.functions.add(new DisplayMessage(work, "Work Function #001"));

		work.functions.add(new DisplayMessage(work, "Work Function #002"));

		apml.runner.queue.add(work);
	}

	//
	public static Object notify(Apmlsubscriber subscriber, ActionEvent ae)
	{
		subscriber.update(null, ae);

		return "success";
	}

	//
	public static Object notifyall(ArrayList<Apmlsubscriber> subscribers, ActionEvent ae)
	{
		for (Apmlsubscriber subscriber : subscribers)
		{
			subscriber.update(null, ae);
		}

		return "success";
	}

	//
	public static Object notify(Apmlsubscriber subscriber, String string)
	{
		subscriber.update(null, null);

		return "success";
	}

	//
	public static Object notifyall(ArrayList<Apmlsubscriber> subscribers, String string)
	{
		for (Apmlsubscriber subscriber : subscribers)
		{
			subscriber.update(null, null);
		}

		return "success";
	}

	//
	public Object preload(Object o, Boolean register)
	{
		System.out.println("Apmlbasesystem doinstantiation() called...");

		Object retval = null;

		try
		{
			retval = o;
		}
		catch (Exception e)
		{
			//
		}
		finally
		{
			if(retval==null) 		return null;

			if(register==false) 	return retval;

			//

			if(retval instanceof Apmlsystem)
			{
				((Apmlsystem)retval).monitor = this;

				mountsystem( (Apmlsystem) retval );
			}

			if(retval instanceof Apmlobject)
			{
				((Apmlobject)retval).monitor = this;

				mountobject( (Apmlobject) retval );
			}

			if(retval instanceof Apmllistener)
			{
				((Apmllistener)retval).monitor = this;

				mountlistener( (Apmllistener) retval );
			}

			if(retval instanceof Apmlsubscriber)
			{
				((Apmlsubscriber)retval).monitor = this;

				mountsubscriber( (Apmlsubscriber) retval );
			}
		}

		return retval;
	}

	//
	public Object preload(Class c, Boolean register)
	{
		System.out.println("Apmlbasesystem doinstantiation() called...");

		Object retval = null;

		try
		{
			retval = c.newInstance();
		}
		catch (Exception e)
		{
			//
		}
		finally
		{
			if(retval==null) 		return null;

			if(register==false) 	return retval;

			//

			if(retval instanceof Apmlsystem)
			{
				((Apmlsystem)retval).monitor = this;

				mountsystem((Apmlsystem) retval);
			}

			if(retval instanceof Apmlobject)
			{
				((Apmlobject)retval).monitor = this;

				mountobject((Apmlobject) retval);
			}

			if(retval instanceof Apmllistener)
			{
				((Apmllistener)retval).monitor = this;

				mountlistener((Apmllistener) retval);
			}

			if(retval instanceof Apmlsubscriber)
			{
				((Apmlsubscriber)retval).monitor = this;

				mountsubscriber((Apmlsubscriber) retval);
			}
		}

		return retval;
	}

	//
	public static void doputelementsonstartup(Map map)
	{
		Apmlbasesystem.map.putAll(map);
	}

	//
	public static void doputelementsonstartup(String[] names, Object[] objects)
	{
		for (int i = 0; i < names.length; i++)
		{
			map.put(names[i], objects[i]);
		}
	}

	//
	public static Map dogetelements()
	{
		return Apmlbasesystem.map;
	}

	//
	public static Object dogetelement(Object o)
	{
		return Apmlbasesystem.map.get(o);
	}

	//
	public final void setapmlfile(String apmlfile)
	{
        this.apmlfile = apmlfile;
	}

	//
	public final void setbasedir(String basedir)
	{
        this.basedir = basedir;
	}

	//
	public final void setdriver(Stddriver driver)
	{
        this.driver = driver;
	}

	//
	public void loadbodi()
	{
		//

		Bodi.setcontext("bodi//development.events.org/{system}");

		Bodi.setcontext("bodi//development.events.org/{system}/{systems}");

		Bodi.setcontext("bodi//development.events.org/{system}/{classes}");

		Bodi.setcontext("bodi//development.events.org/{system}/{state}");

		Bodi.setcontext("bodi//development.events.org/{system}/{listeners}");

		Bodi.setcontext("bodi//development.events.org/{system}/{subscribers}");

		//

		try
		{
			Bodi.context("bodi//development.events.org/{system}").put("apmlsystem", this);

			Bodi.context("bodi//development.events.org/{system}/{systems}").put("apmlsystem.systems", systems);

			Bodi.context("bodi//development.events.org/{system}/{classes}").put("apmlsystem.classes", this.classes);

			Bodi.context("bodi//development.events.org/{system}/{state}").put("apmlsystem.state", this);

			Bodi.context("bodi//development.events.org/{system}/{listeners}").put("apmlsystem.listeners", listeners);

			Bodi.context("bodi//development.events.org/{system}/{subscribers}").put("apmlsystem.subscribers", subscribers);
		}
		catch(Exception e)
        {
            e.printStackTrace();
		}
	}

	//
	public void startsystems()
	{
		for (Apmlsystem system : systems)
        {
            system.start();
		}
	}

	//
	public void initsystems()
	{
		for (Apmlsystem system : systems)
        {
            system.initialize();
		}
	}

	//
	public void runsystems()
	{
		for (Apmlsystem system : systems)
        {
			system.run();
		}
	}

	//
	public void startsystem(Stdsystem system)
	{
		system.start();
	}

	//
	public void initsystem(Stdsystem system)
	{
		system.initialize();
	}

	//
	public void runsystem(Stdsystem system)
	{
		system.run();
	}

	@Override
	public void autostart()
	{
		//
	}

	@Override
	public void init()
	{
		System.out.println("Apmlbasesystem init() called...");

		if (this.apmlfile == null)
			return;

		if (this.basedir == null)
			return;

		//

		this.runner = new ApmlKernelThread(this);

		this.runner.init();

		this.runner.start();

		//

		this.initsystems();
	}

	@Override
	public void pause()
	{
		System.out.println("Apmlbasesystem pause() called...");

		if (this.runner == null)
			return;

		if (this.apmlfile == null)
			return;

		if (this.basedir == null)
			return;

		synchronized (this.runner)
		{
			try
			{
				this.runner.wait();
			}
			catch (Exception e)
			{
				//
			}
		}
	}

	@Override
	public void restart()
	{
		System.out.println("Apmlbasesystem restart() called...");

		if (this.runner == null)
			return;

		if (this.apmlfile == null)
			return;

		if (this.basedir == null)
			return;

		//

		this.stop();

		this.init();

		this.start();

		this.run();
	}

	@Override
	public void resume()
	{
		System.out.println("Apmlbasesystem resume() called...");

		if (this.runner == null)
			return;

		if (this.apmlfile == null)
			return;

		if (this.basedir == null)
			return;

		//

		synchronized (this.runner)
		{
			try
			{
				this.runner.notifyAll();
			}
			catch (Exception e)
			{
				//
			}
		}
	}

	@Override
	public void run()
	{
		System.out.println("Apmlbasesystem run() called...");

		if (this.runner == null)
			return;

		if (this.apmlfile == null)
			return;

		if (this.basedir == null)
			return;

		//

		this.runsystems();
	}

	@Override
	public void start()
	{
		System.out.println("Apmlbasesystem start() called...");

		if (this.runner == null)
			return;

		if (this.apmlfile == null)
			return;

		if (this.basedir == null)
			return;

		//

		this.startsystems();
	}

	@Override
	public void stop()
	{
		System.out.println("Apmlbasesystem stop() called...");

		if (this.runner == null)
			return;

		this.runner.interrupt();
	}

	//
	public void put(Object key, Object value)
	{
        this.properties.put(key, value);
	}

	//
	public void _loadclasses(String basedir)
	{
		System.out.println("Apmlbasesystem _loadclasses() called...");

		try
		{
			classnames = new Fileloader().loadclasses(new File(basedir), null, ".class", new ArrayList());

            for(String eachclass : classnames)
            {
                try
                {
                    System.out.println("Apmlbasesystem adds " + Class.forName(eachclass) + " from classpath " + basedir);

                    this.classes.add(Class.forName(eachclass));
                }
                catch (Exception e)
                {
                    System.out.println("Apmlbasesystem fails to add " + Class.forName(eachclass) + " from classpath " + basedir);
                }
            }
        }
        catch(Exception exception)
		{
			//System.out.println(exception);
		}
	}

	//
	public void _loadclasses(String[] basedirs)
	{
		for(String basedir : basedirs)
        {
            this._loadclasses(basedir);
		}
	}

	//
	public void setproperty(Object object, Object state) throws Exception
	{
        Object certain = this.getproperty(object);

        if(certain==null) throw new Exception("System property ["+object+"] was not found; returning");

        this.properties.put(object, state);
	}

	//
	public Object getproperty(Object object)
	{
        return this.properties.get(object);
	}

	//
	public Object mountobject(Apmlobject object)
	{
		this.objects.add(object);

		return "success";
	}

	//
	public Object mountobjects(ArrayList<Apmlobject> objects)
	{
		this.objects.addAll(objects);

		return "success";
	}

	//
	public Object unmountobject(Apmlobject object)
	{
		this.objects.remove(object);

		return "success";
	}

	//
	public Object unmountobjects(ArrayList<Apmlobject> objects)
	{
		this.objects.removeAll(objects);

		return "success";
	}

	//
	public Object mountlistener(Apmllistener listener)
	{
        this.listeners.add(listener);

        return "success";
	}

	//
	public Object mountlisteners(ArrayList<Apmllistener> listeners)
	{
		this.listeners.addAll(listeners);

        return "success";
	}

	//
	public Object unmountlistener(Apmllistener listener)
	{
        listeners.remove(listener);

        return "success";
	}

	//
	public Object unmountlisteners(ArrayList<Apmllistener> listeners)
	{
		this.listeners.removeAll(listeners);

        return "success";
	}

	//
	public Object mountsubscriber(Apmlsubscriber subscriber)
	{
        subscribers.add(subscriber);

		return "success";
	}

	//
	public Object mountsubscribers(ArrayList<Apmlsubscriber> subscribers)
	{
		this.subscribers.addAll(subscribers);

        return "success";
	}

	//
	public Object mountsystem(Apmlsystem system)
	{
		this.systems.add(system);

		return "success";
	}

	//
	public Object mountsystems(ArrayList<Apmlsystem> systems)
	{
		this.systems.addAll(systems);

		return "success";
	}

	//
	public Object unmountsubscriber(Apmlsubscriber subscriber)
	{
        subscribers.remove(subscriber);

		return "success";
	}

	//
	public Object unmountsubscribers(ArrayList<Apmlsubscriber> subscribers)
	{
		this.subscribers.removeAll(subscribers);

		return "success";
	}

	//
	public Object getlistener(Integer hashcode)
	{
		Apmllistener listener = (Apmllistener) Bodi.context("bodi//development.events.org/{system}/listeners").pull(hashcode);

		return listener;
	}

	//
	public Object getlisteners(String bodistring)
	{
		Apmllistener listeners = (Apmllistener) Bodi.context("bodi//development.events.org/{system}/listeners").pull(bodistring);

		return listeners;
	}

	//
	public Object getsubscriber(String bodistring)
	{
		Apmllistener subscriber = (Apmllistener) Bodi.context("bodi//development.events.org/{system}/subscribers").pull(bodistring);

		return subscriber;
	}

	//
	public ArrayList<Apmlsubscriber> getsubscribers(String bodistring)
	{
		ArrayList<Apmlsubscriber> subscribers = (ArrayList<Apmlsubscriber>) Bodi.context("bodi//development.events.org/{system}/subscribers").pull(bodistring);

		return subscribers;
	}
}
