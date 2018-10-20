package lesson6.resources;

/**
 * @author rassoll
 * @created 05.10.2018
 */
public class ResourceServerController implements ResourceServerControllerMBean
{
	private TestResourceI testResource;

	public ResourceServerController(TestResourceI testResource)
	{
		this.testResource = testResource;
	}

	@Override
	public String getName()
	{
		return testResource.getName();
	}

	@Override
	public int getAge()
	{
		return testResource.getAge();
	}
}
