import java.util.Iterator;

public class Floyd<V,E>{
    public void floyd(Graph<V,E> g){
		Iterator<V> witer = g.iterator();
		while(witer.hasNext()){
			Iterator<V> uiter = g.iterator();
			V w = witer.next();
			while(uiter.hasNext()){
				Iterator<V> viter = g.iterator();
				V u = uiter.next();
				while(viter.hasNext()){
					V v = viter.next();
					if(g.containsEdge(u, w) && g.containsEdge(w, v)){
						Edge<V,E> leg1 = g.getEdge(u,w);
						Edge<V,E> leg2 = g.getEdge(w,v);
						double leg1Dist = (Double) leg1.label();
						double leg2Dist = (Double) leg2.label();
						double newDist = leg1Dist+leg2Dist;
						if(g.containsEdge(u,v)){
							Edge<V,E> across = g.getEdge(u,v);
							double acrossDist = (Double) across.label();
							if(newDist < acrossDist)
								across.setLabel((E)newDist);
						}
						else{
							g.addEdge(u,v,newDist);
						}
					}
				}
			}
		}
    }
}
