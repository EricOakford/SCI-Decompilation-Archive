### Leisure Suit Larry 1 VGA (LSL1)
  Game version 2.1
  Interpreter version 1.000.577

  After compiling the scripts, SCICompanion may later mistakenly identify the resource map format as SCI0, when it should be SCI0+ (yes, I know this is SCI1, but it's a really weird case here)

  There is a chance that attempting to overwrite a savegame will instead create a duplicate save with the same name. This is a known issue in SRDialog::doit. Fortunately, you can work around this by deleting the duplicate save in the save/restore dialog.