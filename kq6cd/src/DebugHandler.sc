;;; Sierra Script 1.0 - (do not remove this comment)
(script# 911)
(include game.sh)
(use Main)
(use Intrface)
(use Kq6Procs)
(use Print)
(use PolyEdit)
(use DlgEdit)
(use WriteFtr)
(use Window)
(use Ego)
(use User)
(use Actor)
(use System)

(public
	debugHandler 0
	proc911_1 1
)

(local
	local0
	local1
)

(procedure (proc911_1 &tmp temp0 temp1 [temp2 5])
	(= temp2 0)
	(= temp1 0)
	(theGame setCursor: normalCursor)
	(while
		(not
			(!=
				(= temp1
					(Print
						font: smallFont
						addText: {Where to, STUD?}
						addEdit: @temp2 5 80
						addButton: 100 {______Opening________} 0 20
						addButton: 200 { Isle of the Crown_} 101 20
						addButton: 300 {__Isle Sacred Mtn__} 0 34
						addButton: 405 {_____Labyrinth_____} 101 34
						addButton: 450 {__Isle of Wonder___} 0 48
						addButton: 500 {___Isle of Beast___} 101 48
						addButton: 550 { Isle of the Mists_} 0 62
						addButton: 600 { Realm of the Dead_} 101 62
						addButton: 730 {__Castle - Beauty__} 0 76
						addButton: 710 {Castle - Magic Door} 101 76
						addButton: -100 {_____< Restore >_____} 0 90
						addButton: 205 {_____Tutorial______} 101 90
						init:
					)
				)
				0
			)
		)
	)
	(if temp2 (= temp1 (ReadNumber @temp2)))
	(if (== temp1 -100) (theGame restore:))
	(if (< howFast 100)
		(= howFast (+ howFast temp1))
	else
		(= howFast (+ howFast 1000))
	)
	(if (OneOf temp1 200 300 450 500 550)
		(Bset fTeleporting)
	)
	(curRoom newRoom: temp1)
)

(procedure (localproc_0161 param1 param2 &tmp [temp0 40])
	(= temp0 0)
	(if (> argc 1) (Format @temp0 911 0 param2))
	(return
		(if (GetInput @temp0 10 param1)
			(ReadNumber @temp0)
		else
			-1
		)
	)
)

(procedure (localproc_019c)
	(if (OneOf (curRoom style?) dpOPEN_SCROLL_RIGHT dpOPEN_SCROLL_LEFT dpOPEN_SCROLL_UP dpOPEN_SCROLL_DOWN)
		(DrawPic (curRoom picture?) dpOPEN_NO_TRANSITION)
	)
)

;export this script as .scr and .hep patch files and place in root game dir to enable debugger
(instance debugHandler of Code
	(properties)
	
	(method (handleEvent pEvent &tmp [temp0 200] temp200 newEvent temp202 temp203 temp204 [temp205 4] temp209 temp210 temp211 temp212 temp213 temp214 temp215 temp216 temp217)
		(switch (pEvent type?)
			(evKEYBOARD
				(pEvent claimed: 1)
				(switch (pEvent message?)
					(KEY_ALT_a
						(= temp203 (cast first:))
						(while temp203
							(= temp204 (NodeValue temp203))
							(Format
								@temp0
								{name: %s\nclass: %s\nview: %d\nloop: %d\ncel: %d\nposn: %d %d %d\nheading: %d\npri: %d\nsignal: $%x\nscaleSignal: $%x\nscaleX: %d\nscaleY: %d\nillBits: $%x\n}
								(temp204 name?)
								((temp204 -super-?) name?)
								(temp204 view?)
								(temp204 loop?)
								(temp204 cel?)
								(temp204 x?)
								(temp204 y?)
								(temp204 z?)
								(temp204 heading?)
								(temp204 priority?)
								(temp204 signal?)
								(temp204 scaleSignal?)
								(temp204 scaleX?)
								(temp204 scaleY?)
								(if
									(or
										(== (temp204 -super-?) Actor)
										(== (temp204 -super-?) Ego)
									)
									(temp204 illegalBits?)
								else
									-1
								)
							)
							(if (not (temp204 scaleSignal?))
								(Print
									addIcon: (temp204 view?) (temp204 loop?) (temp204 cel?) 0 0
									font: smallFont
									addText:
										@temp0
										(CelWide
											(temp204 view?)
											(temp204 loop?)
											(temp204 cel?)
										)
										0
									init:
								)
							else
								(Print font: smallFont addText: @temp0 0 0 init:)
							)
							(= temp203
								(cast next: temp203)
							)
						)
					)
					(KEY_ALT_b
						(if (= local1 (cast first: gEgo))
							(gEgo hide:)
						)
						(PolygonEditor doit:)
						(if local1 (gEgo show:))
					)
					(KEY_ALT_c
						(localproc_019c)
						(Show 4)
						(Animate (cast elements?))
						(repeat
							(if
								(or
									(== ((= pEvent (Event new:)) type?) 1)
									(== (pEvent type?) evKEYBOARD)
								)
								(break)
							)
							(pEvent dispose:)
						)
						(pEvent dispose:)
						(Show 1)
					)
					(KEY_ALT_d
						(if (= debugOn (not debugOn))
							(Prints {On})
						else
							(Prints {Off})
						)
					)
					(KEY_ALT_e (DialogEditor doit:))
					(KEY_ALT_f
						(= temp209 0)
						(= temp209 (GetNumber {Flag #:}))
						(if (Btst temp209)
							(Prints {cleared})
							(Bclr temp209)
						else
							(Prints {set})
							(Bset temp209)
						)
					)
					(KEY_ALT_g
						(= temp0 0)
						(GetInput @temp0 5 {Variable No.})
						(if
						(not (= temp203 (ReadNumber @temp0)))
							(return)
						)
						(= temp0 0)
						(GetInput @temp0 5 {Value})
						(= [theGame temp203] (ReadNumber @temp0))
						(= temp0 0)
					)
					(KEY_ALT_i
						(= temp0 0)
						(= temp203 0)
						(while (!= temp203 100)
							(= temp203
								(Print
									font: smallFont
									addText: {Enter Inv#:}
									addEdit: @temp0 5 80
									addButton: 100 {Outta here!} 0 17
									init:
								)
							)
							(if temp0 (gEgo get: (ReadNumber @temp0)))
							(= temp0 0)
						)
					)
					(KEY_ALT_k
						(= temp200 (GetPort))
						(SetPort 0)
						(= temp216 5)
						(= temp217 16)
						(= temp212 15)
						(= temp213 80)
						(= temp215 (+ temp212 (* 34 temp216)))
						(= temp214 (+ temp213 (* 10 temp217)))
						(= temp210
							(Graph grSAVE_BOX temp212 temp213 temp215 temp214 1)
						)
						(Graph grFILL_BOX temp212 temp213 temp215 temp214 1 255)
						(= temp211 0)
						(while (< temp211 256)
							(Graph
								grFILL_BOX
								(+ temp212 temp216 (* temp216 (/ temp211 8)))
								(+ temp213 temp217 (* 16 (mod temp211 8)))
								(+ temp212 temp216 temp216 (* temp216 (/ temp211 8)))
								(+ temp213 temp217 temp217 (* temp217 (mod temp211 8)))
								1
								temp211
							)
							(++ temp211)
						)
						(Graph grUPDATE_BOX temp212 temp213 temp215 temp214 1)
						(repeat
							(if
								(or
									(== ((= newEvent (Event new:)) type?) 1)
									(== (newEvent type?) 4)
								)
								(break)
							)
							(newEvent dispose:)
						)
						(newEvent dispose:)
						(Graph grRESTORE_BOX temp210)
						(Graph grUPDATE_BOX temp212 temp213 temp215 temp214 1)
						(SetPort temp200)
					)
					(KEY_ALT_m (theGame showMem:))
					(KEY_ALT_l
						(if (u> (MemoryInfo 1) 1536)
							((ScriptID 952) doit:)
						else
							(Prints {No Memory!!})
						)
					)
					(KEY_ALT_p
						(localproc_019c)
						(Show 2)
					)
					(KEY_ALT_q
						(Print
							font: smallFont
							addTextF: {Cur X: %d,Y: %d} (pEvent x?) (pEvent y?)
							init:
						)
					)
					(KEY_ALT_r
						(Print
							font: smallFont
							addTextF:
								{name: %s\nscript: %s\nhorizon: %d\nvanishingX: %d\nvanishingY: %d\npicAngle: %d\nnorth: %d\nsouth: %d\neast: %d\nwest: %d\nstyle: %d\ncurPic: %d_}
								(curRoom name?)
								(if (IsObject (curRoom script?))
									((curRoom script?) name?)
								else
									{none}
								)
								(curRoom horizon?)
								(curRoom vanishingX?)
								(curRoom vanishingY?)
								(curRoom picAngle?)
								(curRoom north?)
								(curRoom south?)
								(curRoom east?)
								(curRoom west?)
								(curRoom style?)
								(curRoom curPic?)
							window: Window
							width: 170
							addTitle: {Current Room}
							init:
						)
					)
					(KEY_ALT_t (proc911_1))
					(KEY_ALT_s
						(= temp203 (cast first:))
						(while temp203
							(if
								(and
									(not
										(&
											((= temp204 (NodeValue temp203)) signal?)
											$0004
										)
									)
									(not (& (temp204 signal?) $0080))
								)
								(Format
									@temp0
									{Updating cast members\nname: %s\nclass: %s\nview: %d\nloop: %d\ncel: %d\nposn: %d %d %d\nheading: %d\npri: %d\nsignal: $%x\nillBits: $%x\n}
									(temp204 name?)
									((temp204 -super-?) name?)
									(temp204 view?)
									(temp204 loop?)
									(temp204 cel?)
									(temp204 x?)
									(temp204 y?)
									(temp204 z?)
									(temp204 heading?)
									(temp204 scaleX?)
									(temp204 scaleY?)
									(if
										(or
											(== (temp204 -super-?) Actor)
											(== (temp204 -super-?) Ego)
										)
										(temp204 illegalBits?)
									else
										-1
									)
								)
								(if (not (temp204 scaleSignal?))
									(Print
										addIcon: (temp204 view?) (temp204 loop?) (temp204 cel?) 0 0
										font: smallFont
										addText:
											@temp0
											(CelWide
												(temp204 view?)
												(temp204 loop?)
												(temp204 cel?)
											)
											0
										window: Window
										init:
									)
								else
									(Print
										font: smallFont
										addText: @temp0 0 0
										window: Window
										init:
									)
								)
							)
							(= temp203
								(cast next: temp203)
							)
						)
					)
					(KEY_ALT_u (user canInput:))
					(KEY_ALT_v (Show 1))
					(KEY_ALT_w (CreateObject doit:))
					(KEY_ALT_y
						(Print
							font: smallFont
							addTextF:
								{vanishing x: %d,y: %d}
								(curRoom vanishingX?)
								(curRoom vanishingY?)
							init:
						)
						(= temp203 (localproc_0161 {vanishingX:}))
						(if (OneOf temp203 -1 0)
						else
							(curRoom vanishingX: temp203)
						)
						(= temp203 (localproc_0161 {vanishingY:}))
						(if (OneOf temp203 -1 0)
						else
							(curRoom vanishingY: temp203)
						)
						(Print
							font: smallFont
							addTextF:
								{vanishing x: %d,y: %d}
								(curRoom vanishingX?)
								(curRoom vanishingY?)
							init:
						)
						(= temp0 0)
					)
					(KEY_ALT_z (= quit 1))
					(KEY_ALT_h
						(Print
							font: smallFont
							addText:
								{ALT-A show Cast\nALT-B Polygon Editor\nALT-C Control map\nALT-D DebugOn toggle\nALT-F Flag set/clr\nALT-G Global set\nALT-I Inv items\nALT-L Log file\nALT-M Memory\nALT-P Priority map\nALT-Q show Cursor Coords\nALT-R Room info\nALT-S Updating cast elements\nALT-T Teleport\nALT-U return User control\nALT-V Visual map\nALT-W feature Writer\nALT-Y Vanishing point adj\nALT-Z QUICK QUIT}
							init:
						)
					)
					(else  (pEvent claimed: 0))
				)
			)
			(evMOUSEBUTTON
				(cond 
					((& (pEvent modifiers?) emCTRL)
						(pEvent claimed: 1)
						(while (!= 2 ((= newEvent (Event new:)) type?))
							((User alterEgo?)
								posn: (newEvent x?) (- (newEvent y?) 10)
								setMotion: 0
							)
							(Animate (cast elements?) 0)
							(newEvent dispose:)
						)
						(newEvent dispose:)
					)
					((& (pEvent modifiers?) emALT)
						(pEvent claimed: 1)
						(= temp200
							(Print
								font: 999
								addTextF: {%d/%d} (pEvent x?) (pEvent y?)
								posn:
									(cond 
										((< (pEvent x?) 20) (pEvent x?))
										((< 300 (pEvent x?)) (- (pEvent x?) 40))
										(else (- (pEvent x?) 20))
									)
									(if (< (pEvent y?) 16)
										(pEvent y?)
									else
										(- (pEvent y?) 6)
									)
								modeless: 1
								init:
							)
						)
						(while (!= 2 ((= newEvent (Event new:)) type?))
							(newEvent dispose:)
						)
						(newEvent dispose:)
						(temp200 dispose:)
					) 
				)
			)
		)
	)
)