# <img src="https://github.com/Software-Hardware-Codesign/Articular-ES/assets/60224159/6d7d9ff3-60f9-476e-85aa-686ca4330b7f" width=120 height=120/> Articular-ES
[![](https://github.com/Software-Hardware-Codesign/Articular-ES/actions/workflows/build-test.yml/badge.svg)](https://github.com/Software-Hardware-Codesign/Articular-ES/actions) [![](https://github.com/Software-Hardware-Codesign/Articular-ES/actions/workflows/build-deploy.yml/badge.svg)](https://repo.maven.apache.org/maven2/io/github/software-hardware-codesign/)


An entity component system (ECS) framework featuring strong articulations among components
from different systems through controller interfaces. The API is powered by a caching system that enables caching data in different
configurations. Operational interactions take place within the system manager through the controller
objects. The API provides a strong abstraction based on the data-centered architecture with the ability to model complex systems, such as: Human Interface Devices (HID) APIs, and language processing and translational APIs.

## Software specification:

| Part | Description | 
|-------------------------------------------|---------------------------------|
| _**Problem**_ | In software detailed design phase, generalization or inheritance, a crucial part of OOP paradigm, has already created a lot of mess in regard to data association between objects, and re-usability or extensibility of these associations which becomes a burden in the implementation phase. | 
| _**General Approach**_ | This is a structural problem (requires a structural architecture), thus a generalized proposed solution is to build solution repositories that take hold of exclusive independent data units (composition applied). A solution repository will also take care of the instructional code by providing action-holder interfaces (strategy pattern applied), then organizational problems can be decomposed into systems and solved by incrementally providing output processed packets using design-patterns. Two commonly used patterns are the recursive pattern, and the finite-state automaton pattern. The architecture, in-use here, can be well categorized under the data-centered architectural patterns. |
| _**Articular-ES Approach**_ | Articular-ES approaches this problem similarly by providing a `system manager`(solution repository) that takes hold of `components`(data units) in a system-first memory format (system-[entity]-component). A system manager is an instantiable object that is once instantiated, it can composite components in their respective systems. A system manager also provides the ability to perform user-defined actions on this memory map format through the strategic patterns. | 
| _**Data caching**_ | Caching data in other formats can help the system manager to provide operational actions on even bizarre memory configurations, for example: (entity-[system]-component); where all the components under an entity can be accessed with a single CPU clock phase without the need to execute a searching algorithm of any sort. |
| _**Articular-ES parts**_ | The main building blocks in articular-es is the _Component_ interface and the _memory maps_, and the composition between these basic constituents is the power of articular-es. System managers provide an interactive environment among systems' components through system controllers, while the _Component_ and its descendents provide the structural capabilities for the framework. ECS modules act as components' collections, and are considered components from an abstractive perspective to enable reusability in the same API, and seemless extensibility. |
| _**Data-pipes**_ | Data-pipes are used for encapsulating data between systems. A data pipe could be registered to an ecs manager, and instantiated when needed. Data pipes can encapsulate algorithms with return value, as well (e.g. filtering algorithms and machine learning algorithms). |
| _**Articular-monkey**_ | Articular-ES is a generalized architectural API. Specializations can be implemented easily bearing in mind the pros and weaknesses. Articular-monkey is a WIP specialization. |

## Provisional architectural component diagram:
<img src="https://github.com/Software-Hardware-Codesign/Articular-ES/blob/master/.assets/component-interface-design.svg"/>

## Development phases: 
- [x] Architecture.
- [x] Detailed design.
- [x] Constructional design.
- [ ] Articular-monkey design.
- [x] Deployment design.
- [x] Testing and tech-demos.
    * [TestArticularManager](https://github.com/Software-Hardware-Codesign/Articular-ES/blob/master/articular-examples/src/main/java/articular/example/TestArticularManager.java): tests the articular manager utility composing both the standard ecs manager, and the cache manager in a embedded data-flow environment.
    * [TestArticularJme](https://github.com/Software-Hardware-Codesign/Articular-ES/blob/master/articular-examples/src/main/java/articular/example/labs/techdemos/jme/TestArticularJme.java): migrated _[TestJaime.java](https://github.com/jMonkeyEngine/jmonkeyengine/blob/master/jme3-examples/src/main/java/jme3test/animation/TestJaime.java)_ to use articular-es; where Jaimes and Cinematics (Components) are built, and added to the JumpKickCinematic System.
    * [TestDataPipes](https://github.com/Software-Hardware-Codesign/Articular-ES/blob/master/articular-examples/src/main/java/articular/example/TestDataPipes.java): tests the new data pipe featured interface in a sensor module example that envisions calculating data using linear algebra, the connection is made between two systems (DataCollector and DataProcessor).
- [ ] Documentation.
- [x] Deployment scripting.
- [x] Deployment.
- [ ] Real life examples (Serial4j).

## In-use architectural patterns: 
- [x] Data-centered architectural pattern.
- [x] Hierarchical architectural pattern.
- [x] Data-flow pipe architectural pattern.

## In-use detailed-design patterns:
- [x] Strategy pattern.
- [x] Template pattern.
- [x] Composite pattern.
- [ ] Finite-state-automata (FSA).

## In-use DSA libraries: 
- [x] Java Collection framework.
- [ ] Arithmos.

## Quick usage: 
```bash
repositories {
    mavenCentral()
}
dependencies {
    implementation "io.github.software-hardware-codesign:articular-es:$version"
}
```

### Appendix

- Overview: 

https://github.com/Software-Hardware-Codesign/Articular-ES/assets/60224159/5ed857ba-3ea9-416c-b936-5bb51bafd6b5

- Resources:

| Software Design | Data-oriented Design (DoD) |
|-----------------|------------------------|
| [![](https://github.com/Software-Hardware-Codesign/Articular-ES/assets/60224159/593dba70-aa23-4a79-94c5-127d7c635393)](https://www.amazon.com/Software-Engineering-Design-Practice-Applied/dp/1439851689) | [![](https://github.com/Software-Hardware-Codesign/Articular-ES/assets/60224159/b8f980d9-1ee5-45f6-8781-ac1f92156e2a)](https://www.amazon.com/Data-oriented-design-engineering-resources-schedules/dp/1916478700) |
| [![](https://github.com/Software-Hardware-Codesign/Articular-ES/assets/60224159/f45be0e4-fe1b-446d-bb95-115ed212ed52)](https://www.amazon.com/Design-Patterns-Object-Oriented-Addison-Wesley-Professional-ebook/dp/B000SEIBB8) | |



