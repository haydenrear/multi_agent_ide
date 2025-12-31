import { ContextProvider } from "@lit/context";
import { A2uiCore } from "@/lib/a2uiBridge";

export type Theme = {
  components: {
    AudioPlayer: Record<string, boolean>;
    Button: Record<string, boolean>;
    Card: Record<string, boolean>;
    Column: Record<string, boolean>;
    CheckBox: {
      container: Record<string, boolean>;
      element: Record<string, boolean>;
      label: Record<string, boolean>;
    };
    DateTimeInput: {
      container: Record<string, boolean>;
      element: Record<string, boolean>;
      label: Record<string, boolean>;
    };
    Divider: Record<string, boolean>;
    Image: {
      all: Record<string, boolean>;
      icon: Record<string, boolean>;
      avatar: Record<string, boolean>;
      smallFeature: Record<string, boolean>;
      mediumFeature: Record<string, boolean>;
      largeFeature: Record<string, boolean>;
      header: Record<string, boolean>;
    };
    Icon: Record<string, boolean>;
    List: Record<string, boolean>;
    Modal: {
      backdrop: Record<string, boolean>;
      element: Record<string, boolean>;
    };
    MultipleChoice: {
      container: Record<string, boolean>;
      element: Record<string, boolean>;
      label: Record<string, boolean>;
    };
    Row: Record<string, boolean>;
    Slider: {
      container: Record<string, boolean>;
      element: Record<string, boolean>;
      label: Record<string, boolean>;
    };
    Tabs: {
      container: Record<string, boolean>;
      element: Record<string, boolean>;
      controls: {
        all: Record<string, boolean>;
        selected: Record<string, boolean>;
      };
    };
    Text: {
      all: Record<string, boolean>;
      h1: Record<string, boolean>;
      h2: Record<string, boolean>;
      h3: Record<string, boolean>;
      h4: Record<string, boolean>;
      h5: Record<string, boolean>;
      caption: Record<string, boolean>;
      body: Record<string, boolean>;
    };
    TextField: {
      container: Record<string, boolean>;
      element: Record<string, boolean>;
      label: Record<string, boolean>;
    };
    Video: Record<string, boolean>;
  };
  elements: {
    a: Record<string, boolean>;
    audio: Record<string, boolean>;
    body: Record<string, boolean>;
    button: Record<string, boolean>;
    h1: Record<string, boolean>;
    h2: Record<string, boolean>;
    h3: Record<string, boolean>;
    h4: Record<string, boolean>;
    h5: Record<string, boolean>;
    iframe: Record<string, boolean>;
    input: Record<string, boolean>;
    p: Record<string, boolean>;
    pre: Record<string, boolean>;
    textarea: Record<string, boolean>;
    video: Record<string, boolean>;
  };
  markdown: {
    p: string[];
    h1: string[];
    h2: string[];
    h3: string[];
    h4: string[];
    h5: string[];
    ul: string[];
    ol: string[];
    li: string[];
    a: string[];
    strong: string[];
    em: string[];
  };
  additionalStyles?: {
    AudioPlayer?: Record<string, string>;
    Button?: Record<string, string>;
    Card?: Record<string, string>;
    Column?: Record<string, string>;
    CheckBox?: Record<string, string>;
    DateTimeInput?: Record<string, string>;
    Divider?: Record<string, string>;
    Heading?: Record<string, string>;
    Icon?: Record<string, string>;
    Image?: Record<string, string>;
    List?: Record<string, string>;
    Modal?: Record<string, string>;
    MultipleChoice?: Record<string, string>;
    Row?: Record<string, string>;
    Slider?: Record<string, string>;
    Tabs?: Record<string, string>;
    Text?:
        | Record<string, string>
        | {
      h1: Record<string, string>;
      h2: Record<string, string>;
      h3: Record<string, string>;
      h4: Record<string, string>;
      h5: Record<string, string>;
      body: Record<string, string>;
      caption: Record<string, string>;
    };
    TextField?: Record<string, string>;
    Video?: Record<string, string>;
  };
};

const DEFAULT_A2UI_THEME: Theme = {
  components: {
    Root: {},
    Row: {},
    Column: {},
    Card: {},
    Button: {},
    Text: {
      all: {},
      h1: {},
      h2: {},
      h3: {},
      h4: {},
      h5: {},
      h6: {},
      caption: {},
      body: {},
    },
    TextField: {},
    Image: {},
    Icon: {},
    Divider: {},
    List: {},
    Checkbox: {},
    Tabs: {},
    Modal: {},
    Slider: {},
    MultipleChoice: {},
    Audio: {},
    Video: {},
    DateTimeInput: {},
  },
  additionalStyles: {},
  markdown: {},
};

class A2uiThemeProvider extends HTMLElement {
  private provider: ContextProvider;

  constructor() {
    super();
    this.provider = new ContextProvider(this, {
      context: A2uiCore.UI.Context.themeContext,
      initialValue: DEFAULT_A2UI_THEME,
    });
  }
}

export const ensureA2uiThemeProvider = () => {
  if (typeof window === "undefined") {
    return;
  }
  if (!customElements.get("a2ui-theme-provider")) {
    customElements.define("a2ui-theme-provider", A2uiThemeProvider);
  }
};
