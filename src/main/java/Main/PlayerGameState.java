package Main;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum PlayerGameState
{
  Won,  Lost,  ShouldActNext,  ShouldWait;
}

