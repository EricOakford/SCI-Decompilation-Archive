;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include game.sh)
(use Main)
(use SQ6PolyEdit)
(use Intrface)
(use String)
(use Print)
(use Feature)
(use Sound)
(use File)
(use User)
(use System)

(public
	debugRm 0
)

(local
	local0
	numBMPs
	local2
)
(instance debugSound of Sound
	(properties)
)

(instance debugRm of Feature
	(properties)
	
	(method (init)
		(super init:)
		(self y: -1)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp eventX eventY newEvent str castFirst temp5 temp6 temp7 temp8 temp9 temp10 temp11)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`?
						(Prints
							{ALT-C - show Cast\nALT-B - caputure BMP\nALT-E - show Ego\nALT-F - edit Features\nALT-G - show/set/clear Flag\nALT-I - get Inventory Item\nALT-P - edit Polygons\nALT-R - show Room info\nALT-S - scaler tool\nALT-T - teleport\nALT-U - handsOn\nALT-X - exit the Game\nCTRL-S - test a sound_}
						)
					)
					(`@b
						(while
							(and
								(< numBMPs 999)
								(= temp5 (String format: {SQ6%03d.BMP} numBMPs))
								(FileIO FileExists (temp5 data?))
							)
							(++ numBMPs)
						)
						(if (< numBMPs 999)
							(Dummy (temp5 data?))
							(Printf {Screen saved as\n___%s} (temp5 data?))
						else
							(Prints
								{Sorry, no can do. How did you get so many files?}
							)
						)
					)
					(`@c
						(if (cast size:)
							(= temp6 (String newWith: 75 {}))
							(= castFirst (cast first:))
							(while castFirst
								(= str (KList LNodeValue castFirst))
								(temp6
									format:
										{class: %s\nname: %s\nview: %d\nloop: %d\ncel: %d\nposn: %d %d %d\nheading: %d\npri: %d\nsignal: $%x\n}
										((str -super-?) name?)
										(str name?)
										(str view?)
										(str loop?)
										(str cel?)
										(str x?)
										(str y?)
										(str z?)
										(str heading?)
										(str priority?)
										(str signal?)
								)
								(if
									(not
										(Print
											addText: (temp6 data?)
											addIcon:
												(str view?)
												(str loop?)
												(str cel?)
												(+ (Print x?) 80)
												(+ (Print y?) 80)
											init:
										)
									)
									(break)
								)
								(= castFirst (cast next: castFirst))
							)
							(temp6 dispose:)
						else
							(Prints {No One Home!})
							(return)
						)
					)
					(`@e
						(= str
							(cond 
								(global102)
								((cast contains: (user alterEgo?)) (user alterEgo?))
								(else (Prints {no ego!}) (return))
							)
						)
						(= temp6 (String newWith: 75 {}))
						(temp6
							format:
								{name: %s\nview: %d\nloop: %d\ncel: %d\nposn: %d %d %d\nheading: %d\npri: %d\nsignal: $%x\nscript: %s\n}
								(str name?)
								(str view?)
								(str loop?)
								(str cel?)
								(str x?)
								(str y?)
								(str z?)
								(str heading?)
								(str priority?)
								(str signal?)
								(if (str script?)
									((str script?) name?)
								else
									{..none..}
								)
						)
						(Print
							addText: (temp6 data?)
							addIcon:
								(str view?)
								(str loop?)
								(str cel?)
								(+ (Print x?) 80)
								(+ (Print y?) 80)
							init:
						)
						(temp6 dispose:)
					)
					(`@f
						(if (not (features size:)) (return))
						(PEditor init:)
						(= castFirst 0)
						(while (< castFirst (features size:))
							(if
							((= str (features at: castFirst)) onMeCheck?)
								(if ((str onMeCheck?) isKindOf: List)
									(= temp10 0)
									(while (< temp10 ((str onMeCheck?) size:))
										(= temp9 ((str onMeCheck?) at: temp10))
										(PEditor addPolygon: temp9)
										(++ temp10)
									)
								else
									(PEditor addPolygon: (str onMeCheck?))
								)
								((PEditor at: (- (PEditor size:) 1))
									name: (str name?)
								)
							)
							(++ castFirst)
						)
						(PEditor show: VMAP)
						(FrameOut)
						(PEditor doit:)
						(= str (String new: 13))
						(= temp11 (String new: 13))
						(temp11 format: {%d.fea} curRoomNum)
						(Print
							addTitle: {Output File Name:}
							addEdit: str 12 0 0 temp11
							init:
						)
						(FrameOut)
						(poly_pol name: (str data?) open: 0)
						(poly_pol
							writeString: {\t\t\t;***************************\0D\n}
						)
						(if (PEditor size:)
							(PEditor eachElementDo: #writeToFile poly_pol 1)
						)
						(poly_pol close:)
						(str dispose:)
						(PEditor dispose:)
					)
					(`@g
						(= temp6 (String newWith: 75 {}))
						(Print
							font: userFont
							y: 100
							addText: {Flag num?}
							addEdit: temp6 5 50
							init:
						)
						(= castFirst (temp6 asInteger:))
						(temp6 dispose:)
						(switch
							(Print
								font: userFont
								y: 50
								addText: (if (Btst castFirst)
									{flag is SET}
								else
									{flag is CLEARED}
								)
								addButton: 1 { set_} 0 12
								addButton: 2 {clear} 0 26
								addButton: -1 {cancel} 0 40
								init:
							)
							(1 (Bset castFirst))
							(2 (Bclr castFirst))
						)
					)
					(`@i
						(theGame setCursor: theDefaultCursor)
						(if
							(and
								(ego has: iBjornBelt)
								(ego has: iBjornChow)
								(ego has: iBrokenClapmaster)
								(ego has: iDuctTape)
								(ego has: iFixedClapmaster)
								(ego has: iPin)
								(ego has: iPliers)
								(ego has: iWrappedPliers)
							)
							(while (!= newEvent -99)
								(= newEvent
									(Print
										addTitle: {* PIGGY ALERT *}
										addText: {You've got everything already!\nYou really are a PIGGY!}
										addButton: -99 {Oink!} 80 13
										init:
									)
								)
							)
							(theGame setCursor: ((theIconBar curIcon?) getCursor:))
						else
							(while (!= newEvent -99)
								(switch
									(= newEvent
										(Print
											addTitle: {Inventory Select-O-Matic\05}
											addButton: -99 {And now, back to the game.} 61 55
											addButton: -98 {I want it all!} 0 55
											addButton: -1 {Bjorn Belt} 0 0
											addButton: -2 {Bjorn Chow\05} 0 13
											addButton: -3 {Broken Clapmaster\05} 0 26
											addButton: -4 {Duct Tape} 0 39
											addButton: -5 {Fixed Clapmaster\05} 100 0
											addButton: -6 {Pin} 100 13
											addButton: -7 {Pliers} 100 26
											addButton: -8 {Wrapped Pliers} 100 39
											init:
										)
									)
									(-1 (ego get: iBjornBelt))
									(-2 (ego get: iBjornChow))
									(-3 (ego get: iBrokenClapmaster))
									(-4 (ego get: iDuctTape))
									(-5 (ego get: iFixedClapmaster))
									(-6 (ego get: iPin))
									(-7 (ego get: iPliers))
									(-8 (ego get: iWrappedPliers))
									(-98
										(ego get: iBjornBelt iBjornChow iBrokenClapmaster
											iDuctTape iFixedClapmaster iPin iPliers iWrappedPliers
										)
										(theGame setCursor: ((theIconBar curIcon?) getCursor:))
										(Print
											addTitle: {* PIGGY ALERT *}
											addText: {You've got it PIGGY!}
											init:
										)
										(= newEvent -99)
									)
									(else 
										(theGame setCursor: ((theIconBar curIcon?) getCursor:))
									)
								)
							)
						)
					)
					(`@p
						(PEditor init:)
						(if (curRoom obstacles?)
							(PEditor readPolygonsFromList: (curRoom obstacles?))
						)
						(PEditor doit:)
						(= str (String new: StrToInt))
						(= temp11 (String new: StrToInt))
						(temp11 format: {%d.pol} curRoomNum)
						(Print
							addTitle: {Output File Name:}
							addEdit: str 12 0 0 temp11
							init:
						)
						(FrameOut)
						(poly_pol name: (str data?) open: 0)
						(poly_pol writeString: {\t\t;********************\0D\n})
						(poly_pol writeString: {\t\t(curRoom addObstacle:\0D\n})
						(if (PEditor size:)
							(PEditor eachElementDo: #writeToFile poly_pol)
						)
						(poly_pol writeString: {\t\t)\0D\n})
						(poly_pol close:)
						(str dispose:)
						(PEditor dispose:)
					)
					(`@r
						(Printf
							{name: %s\nnumber: %d\npicture: %d\nstyle: %d\nhorizon: %d\nnorth: %d\nsouth: %d\neast: %d\nwest: %d\nscript: %s_}
							(curRoom name?)
							curRoomNum
							(curRoom picture?)
							(curRoom style?)
							(curRoom horizon?)
							(curRoom north?)
							(curRoom south?)
							(curRoom east?)
							(curRoom west?)
							(if (curRoom script?)
								((curRoom script?) name?)
							else
								{..none..}
							)
							78
							120
						)
					)
					(`@s
						((ScriptID 35 0) doit:)
					)
					(`^s
						(= castFirst (GetNumber {setLoop?}))
						(= str (GetNumber {which sound number?}))
						(debugSound setLoop: castFirst number: str play:)
					)
					(`@t
						(= temp7 (GetNumber {Which room number?}))
						(curRoom newRoom: temp7)
					)
					(`@u (theGame handsOn:))
					(`@x (= quit TRUE))
					(else  (event claimed: FALSE))
				)
			)
			(mouseDown
				(switch (event modifiers?)
					(13 0)
					(14 0)
					(12
						(event claimed: TRUE)
						(event globalize:)
						(= eventX (event x?))
						(= eventY (event y?))
						(event localize: (cast plane?))
						(Printf
							{global: %d/%d\n local: %d/%d}
							eventX
							eventY
							(event x?)
							(event y?)
							75
							160
							10
							42
							999
						)
					)
					(5
						(event type: keyDown message: 4864)
						(self handleEvent: event)
					)
					(6
						(event type: keyDown message: 4608)
						(self handleEvent: event)
					)
					(9 0)
					(10 0)
					(shiftRight 0)
					(shiftLeft 0)
					(ctrlDown 0)
					(altDown
						(event claimed: TRUE)
						(= temp8 (if global102 else (User alterEgo?)))
						(ego setMotion: 0)
						(while (!= ((= newEvent (Event new:)) type?) 2)
							(newEvent localize: (cast plane?))
							(temp8 x: (newEvent x?) y: (newEvent y?))
							(if (temp8 scaler?) ((temp8 scaler?) doit:))
							(UpdateScreenItem temp8)
							(FrameOut)
							(newEvent dispose:)
						)
						(newEvent dispose:)
					)
				)
			)
		)
	)
)

(instance poly_pol of File
	(properties
		name "poly.pol"
	)
)
