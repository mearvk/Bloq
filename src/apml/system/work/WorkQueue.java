package apml.system.work;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class WorkQueue implements Queue
{
	LinkedList list = new LinkedList<Work>();

	@Override
	public int size()
	{
		return this.list.size();
	}

	@Override
	public boolean isEmpty()
	{
		return this.list.size() == 0;
	}

	@Override
	public boolean contains(Object o)
	{
		return this.list.contains(o);
	}

	@Override
	public Iterator iterator()
	{
		return this.list.iterator();
	}

	@Override
	public Work[] toArray()
	{
		return (Work[]) this.list.toArray(this.list.toArray());
	}

	@Override
	public Work[] toArray(Object[] a)
	{
		return (Work[]) this.list.toArray(a);
	}

	@Override
	public boolean add(Object o)
	{
		return this.list.add(o);
	}

	@Override
	public boolean remove(Object o)
	{
		return this.list.remove(o);
	}

	@Override
	public boolean addAll(Collection c)
	{
		return this.addAll(c);
	}

	@Override
	public void clear()
	{
		this.list.clear();
	}

	@Override
	public boolean retainAll(Collection c)
	{
		return this.list.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection c)
	{
		return this.list.removeAll(c);
	}

	@Override
	public boolean containsAll(Collection c)
	{
		return this.list.containsAll(c);
	}

	@Override
	public boolean offer(Object o)
	{
		return this.list.offer(o);
	}

	@Override
	public Work remove()
	{
		return (Work) this.list.remove();
	}

	@Override
	public Work poll()
	{
		return (Work) this.list.poll();
	}

	@Override
	public Work element()
	{
		return (Work) this.list.element();
	}

	@Override
	public Work peek()
	{
		return (Work) this.list.peek();
	}
}
