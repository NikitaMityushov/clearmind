## Gitflow Workflow
- The Gitflow workflow consists of two main branches: develop and main, as well as a set of supporting branches.

### main branch
- The main branch is the branch that contains the production-ready code. It should always contain the latest stable release, and all code in this branch should be thoroughly tested and ready for deployment.

### develop branch
- The develop branch is the main branch where all development work takes place. It should contain the latest code changes that are being worked on by the team. When new features or bug fixes are ready for testing, they should be merged into the develop branch.

### feature branches
- Feature branches are used to implement new features or enhancements. They should be created from the develop branch and should be named according to the feature being implemented. For example, a feature branch for implementing a new login screen SHOULD be named feature/login-screen. When a feature is complete, it should be merged back into the develop branch.

### release branches
- Release branches are used to prepare for a new release. They should be created from the develop branch and should be named according to the release being prepared. For example, a release branch for preparing for the first release of an Android app SHOULD be named release/1.0.0.
- Release branches should be used to stabilize the code, fix any remaining bugs, and perform final testing before deploying to production. When the release is ready, it should be merged into the master branch and tagged with the release version number.

### hotfix branches
- Hotfix branches are used to quickly fix critical bugs in the production code. They should be created from the master branch and should be named according to the issue being fixed. For example, a hotfix branch for fixing a critical login bug might be named hotfix/login-bug.
- When the hotfix is complete, it should be merged back into both the master and develop branches.
