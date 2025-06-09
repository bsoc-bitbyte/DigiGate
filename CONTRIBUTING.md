
# Contributing to DigiGate

## Quick Links

* [Getting Started](#getting-started)

    * [Issues](#issues)
    * [Pull Requests](#pull-requests)

        * [Fork & Clone](#fork--clone)
        * [Work on Pull Request](#work-on-pull-request)
        * [Keep Your Fork Updated](#keep-your-fork-updated)
* [Guidelines](#guidelines)
* [Need Help?](#need-help)

---

## Getting Started

We keep things structured but simple.
You’ll contribute through **Issues** (bugs, features, UI tasks) and **Pull Requests** (your solutions). But first:

* Check if an Issue or PR already exists before creating a new one.
* One change = One PR. Keep things focused.
* We try to review quickly, but delays can happen — a friendly follow-up is welcome.

---

### Issues

Issues act as our to-do list, idea board, and bug tracker.

You can:

* Report bugs
* Suggest meaningful enhancements
* Ask relevant questions

Use the provided templates to ensure clarity. If your concern already exists as an issue, contribute to that thread instead of creating a duplicate.

---

### Pull Requests

Pull Requests are where you showcase your contribution.

A good PR:

* Addresses a single issue cleanly
* Uses the provided template
* Avoids mixing style changes with major features

Start small — UI components are a great entry point. As you progress, you can take on more complex features like logic and backend integrations.

---

#### Fork & Clone

To work locally:

```bash
# Fork the repository on GitHub, then:
git clone https://github.com/YOUR-USERNAME/DigiGate.git
cd DigiGate
git remote add upstream https://github.com/bsoc-bitbyte/DigiGate.git
git remote -v
```

* `origin` points to your fork
* `upstream` points to the original DigiGate repo

---

#### Work on Pull Request

```bash
# Create a new branch for your changes
git checkout -b your-branch-name

# Make your changes, test them, then:
git add .
git commit -m "Add: Description of your feature or fix"

# Push to your fork
git push origin your-branch-name
```

Then, go to GitHub, open a pull request from your branch, and fill in the PR template.

If you need to make updates, just push more commits to the same branch — the PR will auto-update.

**Important**: Never commit directly to `main` on your fork. Keep it clean.

---

#### Keep Your Fork Updated

Keep your fork in sync with the main repository:

```bash
git checkout main
git pull upstream main
git push origin main
```

Do this regularly or before starting new work.

---

## Guidelines

Please follow these contribution standards:

* Only mentors or maintainers will create issues related to features, bugs, or assignments.
* Create an issue only if it is a genuine bug or valuable enhancement — avoid unnecessary or spam issues.
* Before working on any issue, comment your approach in detail.
* The issue will be assigned based on the most suitable approach — do not spam multiple issues.
* Once assigned, focus solely on that issue — avoid contributing to others in parallel.
* Maintain a clean commit history and write clear, meaningful pull requests.
* Submitting AI-generated code is strictly prohibited and will result in removal from the project.

---

## Need Help?

Join our [BSoC Discord](https://discord.gg/wsTZBRMqdg) and ask questions in the appropriate channels.
Be clear and specific in your message, and someone will assist you.

---

## Final Notes

* Make sure to read the [README.md](./README.md) for project context and setup instructions.
* If you're ready, let's build something great together.