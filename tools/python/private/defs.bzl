# Copyright 2019 The Bazel Authors. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

"""This is a Bazel-internal file; do not load() it!

This file replicates the behavior of the macros in bazelbuild/rules_python's
`//python:defs.bzl` file, allowing the four native rules to be used even with
`--incompatible_load_python_rules_from_bzl` enabled.

We need this file because Bazel's own codebase may not depend on rules_python.
"""

_MIGRATION_TAG = "__PYTHON_RULES_MIGRATION_DO_NOT_USE_WILL_BREAK__"

def _add_tags(attrs):
    if "tags" in attrs and attrs["tags"] != None:
        attrs["tags"] += [_MIGRATION_TAG]
    else:
        attrs["tags"] = [_MIGRATION_TAG]
    return attrs

def py_library(**attrs):
    native.py_library(**_add_tags(attrs))

def py_binary(**attrs):
    native.py_binary(**_add_tags(attrs))

def py_test(**attrs):
    native.py_test(**_add_tags(attrs))

def py_runtime(**attrs):
    native.py_runtime(**_add_tags(attrs))
