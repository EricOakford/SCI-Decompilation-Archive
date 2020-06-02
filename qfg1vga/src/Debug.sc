;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Intrface)
(use Procs)
(use PolyEdit)
(use WriteFtr)
(use Print)
(use Feature)
(use Sound)
(use User)
(use Window)
(use Actor)
(use System)

(public
	debugRoom 0
)

(local
	[local0 4]
)
(instance debugRoom of Feature
	(properties)
	
	(method (init)
		(super init:)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [str 160] evt obj nX nY i n nr)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`?
						(Prints
							{Key commands:\n
							ALT-A show cAst\n
							ALT-O create Obstacles\n
							ALT-F (vacant)\n
							ALT-W Write features\n
							ALT-Y (vacant)\n
							ALT-R show Room\n
							ALT-E (vacant)\n
							ALT-M show Memory\n
							ALT-T Teleport\n
							ALT-V Visual\n
							ALT-P Priority\n
							ALT-C Control
							}
						)
						(Prints
							{ALT-I get InvItem\n
							ALT-S learn a new Spell\n
							ALT-G Set/Clear flag\n
							ALT-B set ego's Bucks\n
							ALT-D toggle debugging\n
							ALT-H set Hour of day\n
							ALT-K set one of ego's sKills\n
							ALT-X make ego eXtra special\n
							ALT-U return User control and input\n
							CTRL-L note Logger
							}
						)
					)
					(`@e
					)
					(`@i
						(= i (GetNumber {Inventory item #:}))
						(= nr (GetNumber {How many?}))
						(ego get: i nr)
					)
					(`@t
						(if
						(> (= nr (GetNumber {Which room number?})) 0)
							(curRoom newRoom: nr)
						)
					)
					(`@z
						(Prints
							{Debug: CLEAR THE CURRENT PALETTE. Note: This is a one way trip.}
						)
						(PalVary PALVARYREVERSE 0)
					)
					(`@h
						(= str 0)
						(= str (GetNumber {Hour: (1 - 24)}))
						(FixTime str)
					)
					(`@v
						(Show VMAP)
					)
					(`@p
						(Show PMAP)
					)
					(`@c
						(Show CMAP)
					)
					(`@d
						(if debugging
							(= debugging FALSE)
							(Prints {debugging off})
						else
							(= debugging TRUE)
							(Prints {debugging on})
						)
					)
					(`@m
						(theGame showMem:)
					)
					(`@s	;in the works
						(= i OPEN)
						(while (< i (+ NUMSTATS 1))
							(if (not [egoStats i])
								(= [egoStats i] 10)
								(= i 999)
								(Printf @str {[egoStats i] is %d} [egoStats i])
							)
							(++ i)
						)
					)
					(`@n 0)
					(`@a
						(= i (cast first:))
						(while i
							(= obj (NodeValue i))
							(Format
								@str
								{class: %s\nview: %d\nloop: %d\ncel: %d\nposn: %d %d %d\nheading: %d\npri: %d\nsignal: $%x\nillBits: $%x\n}
								((obj -super-?) name?)
								(obj view?)
								(obj loop?)
								(obj cel?)
								(obj x?)
								(obj y?)
								(obj z?)
								(obj heading?)
								(obj priority?)
								(obj signal?)
								(if (obj isKindOf: Actor)
									(obj illegalBits?)
								else
									-1
								)
							)
							(if
								(not
									(Print
										addText: @str
										window: SysWindow
										addIcon:
											(obj view?)
											(obj loop?)
											(obj cel?)
											(+ (Print x?) 80)
											(Print y?)
										init:
									)
								)
								(break)
							)
							(= i (cast next: i))
						)
					)
					(`@o
						(PolygonEditor doit:)
					)
					(`@f
					)
					(`@g
						(= i (GetNumber {Flag Number:}))
						(if (Btst i)
							(Prints {clearing flag})
							(Bclr i)
						else
							(Prints {setting flag})
							(Bset i)
						)
					)
					(`@w
						(CreateObject doit:)
					)
					(`@y
					)
					(`@r
						(Format @str
							{name: %s\nnumber: %d\ncurrent pic: %d\nstyle: %d\nhorizon: %d\nnorth: %d\nsouth: %d\neast: %d\nwest: %d\nscript: %s_}
							(curRoom name?)
							curRoomNum
							(curRoom curPic?)
							(curRoom style?)
							(curRoom horizon?)
							(curRoom north?)
							(curRoom south?)
							(curRoom east?)
							(curRoom west?)
							(if (IsObject (curRoom script?))
								((curRoom script?) name?)
							else
								{..none..}
							)
						)
						(Print width: 120 addText: @str init:)
						(theGame showMem:)
					)
					(`@u
						(HandsOn)
					)
					(`@b	;in the works
						(Printf @str
							{You have %d gold and %d silvers.}
							((inventory at: iGold) amount?)
							((inventory at: iSilver) amount?)
						)
						((inventory at: iGold) amount: (GetNumber {Enter Gold:}))
						((inventory at: iSilver) amount: (GetNumber {Enter Silvers:}))
					)
					(`@k
						(= n (GetNumber {Change which Stat/Skill?}))
						(= [egoStats n]
							(GetNumber {Enter new value:} [egoStats n])
						)
					)
					(`@x
						(= nr (GetNumber {Set stats to what?}))
						(= n 0)
						(while (< n OPEN)	;everything before the spells
							(= [egoStats n] nr)
							(++ n)
						)
						(= [egoStats EXPER] 1900)
						(= [egoStats HEALTH] (MaxHealth))
						(= [egoStats STAMINA] (MaxStamina))
						(= [egoStats MANA] (MaxMana))
						(= i 0)
						(while (< i TRIGGER)
							(= [egoStats (+ 20 i)] nr)
							(++ i)
						)
						(Prints {Why, you feel better already!})
					)
					(`@l)
					(`^l
						((ScriptID LOGGER) doit: sysLogPath 0)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		)
	)
)