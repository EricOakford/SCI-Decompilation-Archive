;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use GloryPoly)
(use Intrface)
(use String)
(use Print)
(use Feature)
(use Sound)
(use File)
(use User)
(use System)

(public
	debugRoom 0
)

(local
	[local0 4]
)
(instance debugRoom of Feature
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
	
	(method (handleEvent event &tmp evt temp1 [temp2 2] i temp5 str temp7 castFirst temp9 temp10 [temp11 6] temp17)
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
							ALT-F Show Features\n
							ALT-W Write features\n
							ALT-Y show pallete\n
							ALT-R show Room\n
							ALT-E show Ego\n
							ALT-M show Memory\n
							ALT-T Teleport\n
							ALT-V Visual\n
							ALT-P Priority\n
							ALT-C Learn a new Spell\n}
						)
						(Prints
							{ALT-I get InvItem\n
							ALT-G Set/Clear flag\n
							ALT-B set ego's Bucks\n
							ALT-D toggle debugging\n
							ALT-H set Hour of day\n
							ALT-K set one of ego's sKills\n
							ALT-X make ego eXtra special\n
							ALT-U return User control and input\n
							ALT-S Shift user alterego 
							CTRL L note Logger}
						)
					)
					(`@e
						(= temp17 (if userAlterEgo else (User alterEgo?)))
						(= str (String new:))
						(str
							format:
								{name: %s\n
								view: %d\n
								loop: %d\n
								cel: %d\n
								posn: %d %d %d\n
								heading: %d\n
								pri: %d\n
								signal: %d\n\n\n\n\n\n\n}
								(temp17 name?)
								(temp17 view?)
								(temp17 loop?)
								(temp17 cel?)
								(temp17 x?)
								(temp17 y?)
								(temp17 z?)
								(temp17 heading?)
								(temp17 priority?)
								(temp17 signal?)
						)
						(Print
							width: 300
							addIcon: (temp17 view?) (temp17 loop?) (temp17 cel?) 120 80
							addText: str 0 0
							init:
						)
						(str dispose:)
					)
					(`@i
						(= i (GetNumber {Inventory item #:}))
						(ego get: i)
					)
					(1
						(for ((= i 1)) (< i iLastInvItem) ((++ i))
							((inventory at: i)
								amount: (+ ((inventory at: i) amount?) 1)
								owner: ego
							)
						)
					)
					(`@t
						(if (> (= castFirst (GetNumber {Which room number?})) 0)
							(curRoom newRoom: castFirst)
						)
					)
					(`@z
						(Prints
							{Debug: CLEAR THE CURRENT PALETTE. Note: This is a one way trip.}
						)
						(PalVary PalVaryReverse 0)
					)
					(`@h
						((ScriptID TIME 4) init: (GetNumber {Hour: (1 - 24)}) 0)
					)
					(`@v)
					(`@p)
					(`@d
						(if debugging
							(= debugging 0)
							(Prints {debugging off})
						else
							(= debugging 1)
							(Prints {debugging on})
						)
					)
					(`@m
						(theGame showMem:)
					)
					(`@s
						(= i (GetNumber {cast member #?}))
						(= userAlterEgo (cast at: i))
					)
					(`@n 0)
					(`@a
						(= str (String new: 200))
						(= castFirst (cast first:))
						(while castFirst
							(= temp1 (NodeValue castFirst))
							(str
								format:
									{name: %s\n
									class: %s\n
									view: %d\n
									loop: %d\n
									cel: %d\n
									posn: %d %d %d\n
									heading: %d\n
									pri: %d\n
									signal: $%x\n\n\n\n\n\n\n\n\n\n\n}
									(temp1 name?)
									((temp1 -super-?) name?)
									(temp1 view?)
									(temp1 loop?)
									(temp1 cel?)
									(temp1 x?)
									(temp1 y?)
									(temp1 z?)
									(temp1 heading?)
									(temp1 priority?)
									(temp1 signal?)
							)
							(Print
								width: 300
								addIcon: (temp1 view?) (temp1 loop?) (temp1 cel?) 120 80
								addText: str 0 0
								init:
							)
							(FrameOut)
							(= castFirst (cast next: castFirst))
						)
						(str dispose:)
					)
					(`@o
						;EO: This game does not contain the needed Polygon Editor.
						; Thus, this command does nothing.
;;;						(PEditor init:)
;;;						(if (curRoom obstacles?)
;;;							(PEditor readPolygonsFromList: (curRoom obstacles?))
;;;						)
;;;						(PEditor doit:)
;;;						(= temp1 (String new: 13))
;;;						(= temp10 (String new: 13))
;;;						(temp10 format: {%d.pol} curRoomNum)
;;;						(Print
;;;							font: 300
;;;							fore: 0
;;;							back: 255
;;;							skip: 255
;;;							largeAlp: 0
;;;							addText: {Output File Name:} 0 0
;;;							addEdit: temp1 12 0 12 temp10
;;;							init:
;;;						)
;;;						(FrameOut)
;;;						(poly_pol name: (temp1 data?) open: 0)
;;;						(poly_pol writeString: {\t\t;********************\0D\n})
;;;						(poly_pol writeString: {\t\t(curRoom addObstacle:\0D\n})
;;;						(if (PEditor size:)
;;;							(PEditor eachElementDo: #writeToFile poly_pol)
;;;						)
;;;						(poly_pol writeString: {\t\t)\0D\n})
;;;						(poly_pol close:)
;;;						(temp1 dispose:)
;;;						(PEditor dispose:)
					)
					(`@f)
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
					(`@w)
					(`@y)
					(`@r
						(= str (String new: 100))
						(str
							format:
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
								(if (curRoom script?)
									((curRoom script?) name?)
								else
									{..none..}
								)
						)
						(Prints str)
						(str dispose:)
						(theGame showMem:)
					)
					(`@u
						(User canInput: 1 canControl: 1)
						(theIconBar enable: 0 1 2 3 4 5 6 7)
					)
					(`@b
						(Printf
							{You have %d Crowns and %d Kopeks.}
							((inventory at: 0) amount?)
							kopeks
						)
						((inventory at: 0) amount: (GetNumber {Enter Crowns:}))
						(if
							(or
								(= kopeks (GetNumber {Enter Kopeks:}))
								((inventory at: 0) amount?)
							)
							((inventory at: 0) message: 15)
						)
					)
					(`@k
						(= temp5 (GetNumber {Change which Stat/Skill?}))
						(= [egoStats temp5]
							(GetNumber {Enter new value:} [egoStats temp5])
						)
					)
					(`@x
						(= castFirst (GetNumber {Set stats to what?}))
						(= temp5 0)
						(while (< temp5 20)
							(= [egoStats temp5] castFirst)
							(++ temp5)
						)
						(= [egoStats 16] 1900)
						(= [egoStats 17] (ego maxHealth:))
						(= [egoStats 18] (ego maxStamina:))
						(= [egoStats 19] (ego maxMana:))
						(= i 0)
						(while (< i 22)
							(= [egoStats (+ 20 i)] castFirst)
							(++ i)
						)
						(= paladinStat 1)
						(Bset 20)
						((inventory at: 18) state: 1)
						((inventory at: 19) state: 1)
						((ScriptID 0 21) doit: curRoomNum)
					)
					(`@l)
					(`@c
						(for ((= i OPEN)) (< i NUM_ATTRIBS) ((++ i))
							(if (not [egoStats i])
								(= [egoStats i] 10)
								(= i 999)
								(Printf {[egoStats i] is %d} [egoStats i])
							)
						)
					)
					(KEY_CLEAR
						((ScriptID LOGGER) doit: sysLogPath 0)
					)
					(KEY_PAUSE
						(= temp5 (GetNumber {which sound number?}))
						(theMusic number: temp5 setLoop: 0 play:)
					)
					(else  (event claimed: 0))
				)
			)
			(mouseDown
				(switch (event modifiers?)
					(13 0)
					(14 0)
					(12
						(event claimed: 1)
						(= str (String new:))
						(str format: {%d/%d} (event x?) (event y?))
						(= temp7
							(Print
								addText: (str data?)
								posn: 160 10
								font: 999
								init:
							)
						)
						(while (!= 2 ((= evt (Event new:)) type?))
							(evt dispose:)
						)
						(evt dispose:)
						(str dispose:)
					)
					(5
						(event type: 4 message: 4864)
						(self handleEvent: event)
					)
					(6
						(event type: 4 message: 4608)
						(self handleEvent: event)
					)
					(9 0)
					(10 0)
					(emRIGHT_SHIFT 0)
					(emLEFT_SHIFT 0)
					(emCTRL 0)
					(emALT
						(event claimed: 1)
						(= temp17 (if userAlterEgo else (User alterEgo?)))
						(ego setMotion: 0)
						(while (!= ((= evt (Event new:)) type?) 2)
							(temp17 x: (evt x?) y: (- (evt y?) 10))
							(UpdateScreenItem temp17)
							(FrameOut)
							(evt dispose:)
						)
						(evt dispose:)
					)
				)
			)
		)
	)
)

(instance soundTest of Sound)

(instance poly_pol of File
	(properties
		name "poly.pol"
	)
	
	(method (dispose)
		(self close:)
		(super dispose:)
	)
	
	(method (open param1)
		(= handle
			(switch argc
				(0 (FileIO fiOPEN name 0))
				(1 (FileIO fiOPEN name param1))
				(else  0)
			)
		)
		(if (== handle -1) (= handle 0))
		(return (if handle self else 0))
	)
	
	(method (writeString param1 &tmp temp0)
		(if (not handle) (self open:))
		(if handle
			(= temp0 0)
			(while (< temp0 argc)
				(if
					(not
						(FileIO fiWRITE_STRING handle (KString 9 [param1 temp0]))
					)
					(return 0)
				)
				(++ temp0)
			)
		)
		(return 1)
	)
	
	(method (close)
		(if handle (FileIO fiCLOSE handle) (= handle 0))
	)
)
