d happen is
            // when the user puts additional classes into the JAXB-generated
            // package and pass them to JAXBContext.newInstance().
            // Under normal use, this shouldn't happen.

            // anyway, bail out now.
            // if you hit this problem and wondering how to get around the problem,
            // subscribe and send a note to users@jaxb.dev.java.net (http://jaxb.dev.java.net/)
            throw new JAXBException("Unable to find a JAXB implement