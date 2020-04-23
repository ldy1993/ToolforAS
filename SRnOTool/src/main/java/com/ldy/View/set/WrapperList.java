package com.ldy.View.set;

import com.ldy.View.CustomWidget.ViewGroup.LeftDragItem.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public  class WrapperList<T> extends ArrayList<LeftDragWrapperBean<T>> {
		private static final long serialVersionUID = -6719637649250754727L;
		private ArrayList<T> innerList;
		public WrapperList(ArrayList<T> list) {
			this.innerList = list;
			if (this.innerList != null) {
				notifyChanged();
			}
		}
		public List<LeftDragWrapperBean<T>> creatLeftDragWrapperBeanList(ArrayList<T> innerList) {
			List<LeftDragWrapperBean<T>> List=new ArrayList<LeftDragWrapperBean<T>>();
			for(T o : innerList) {
				LeftDragWrapperBean<T> bean = new LeftDragWrapperBean<>();
				bean.isDragged = false;
				bean.value = o;
				List.add(bean);
			}
			return List;
		}
		public void notifyChanged() {
			super.clear();
			for(T o : innerList) {
				LeftDragWrapperBean<T> bean = new LeftDragWrapperBean<>();
				bean.isDragged = false;
				bean.value = o;
				super.add(bean);
			}
		}
		
		@Override
		public void add(int index, LeftDragWrapperBean<T> object) {
			innerList.add(index, object.value);
			super.add(index, object);
		}
		
		@Override
		public boolean add(LeftDragWrapperBean<T> object) {
			innerList.add(object.value);
			return super.add(object);
		}
		
		@Override
		public void clear() {
			innerList.clear();
			super.clear();
		}
		
		@Override
		public LeftDragWrapperBean<T> remove(int index) {
			innerList.remove(index);
			return super.remove(index);
		}
		
		@Override
		public boolean remove(Object object) {
			innerList.remove(((LeftDragWrapperBean)object).value);
			return super.remove(object);
		}
		
		@Override
		public boolean removeAll(Collection<?> collection) {
			throw new UnsupportedOperationException("removeAll(Collection) is not supported in WrapperList");
		}
		
		@Override
		public boolean addAll(Collection<? extends LeftDragWrapperBean<T>> collection) {
			for(LeftDragWrapperBean<T> bean : collection) {
				innerList.add(bean.value);
			}
			return super.addAll(collection);
		}
		
		@Override
		public boolean addAll(int index, Collection<? extends LeftDragWrapperBean<T>> collection) {
			throw new UnsupportedOperationException("addAll(int, Collection) is not supported in WrapperList");
		}
	}