package Main;

import Lists.ListHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="fullMap")
@XmlAccessorType(XmlAccessType.NONE)
public final class FullMap
{
  @XmlElementWrapper(name="mapNodes")
  @XmlElement(name="mapNode")
  private final List<FullMapNode> mapNodes = new ArrayList();
  

  public FullMap() {}
  

  public FullMap(List<FullMapNode> mapNodes)
  {
    if (ListHelper.hasValue(mapNodes)) {
      this.mapNodes.addAll(mapNodes);
    }
  }
  

  public List<FullMapNode> getMapNodes()
  {
    return Collections.unmodifiableList(mapNodes);
  }
}