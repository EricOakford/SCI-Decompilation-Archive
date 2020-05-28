;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUG)
(include game.sh)
(use Main)
(use Intrface)
(use Print)
(use Dialog)
(use PolyEdit)
(use WriteFtr)
(use Feature)
(use Window)
(use Invent)
(use User)
(use System)

(public
	debugRm 0
)

(local
	newDButton_2
	newDButton
)
(procedure (UseFlagObject)
	(Print
		addText: {Flag Object?} 0 0
		addButton: 1 {yes} 10 20
		addButton: 0 {no} 10 40
		init:
	)
)

(instance debugRm of Feature
	
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
	
	(method (handleEvent event &tmp evt obj [temp2 4] [str 200] savePort i temp208 saveBits color t l b r temp215 temp216 userAlterEgo)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(event claimed: TRUE)
				(switch (event message?)
					(`?
						(Print font: 999)
						(Prints
							{ALT-A - show cAst\n
							ALT-B - show flag value\n
							ALT-C - show Control screen\n
							ALT-E - show Ego\n
							ALT-F - show Features\n
							ALT-G - set Global\n
							ALT-I - get Inventory Item\n
							ALT-J - Note Logger for QA\n
							ALT-K - set flag\n
							ALT-L - cLear flag\n
							ALT-M - show Memory\n
							ALT-O - create Obstacles\n
							ALT-P - show Priority screen\n
							ALT-R - show Room info\n
							ALT-S - Switch mouse actor\n
							ALT-T - Teleport\n
							ALT-U - HandsOn\n
							ALT-V - show Visual screen\n
							ALT-W - Write features\n
							ALT-X - eXit the game\n
							ALT-Y - show pallete}
						)
					)
					(`@a
						(= i (cast first:))
						(while i
							(= obj (NodeValue i))
							(= temp215
								(CelHigh (obj view?) (obj loop?) (obj cel?))
							)
							(Format
								@str
								{NAME: %s\n\nview: %d\nloop: %d\ncel: %d\nposn: %d %d %d\nheading: %d\npriority: %d\nsignal: $%x\n}
								(obj name?)
								(obj view?)
								(obj loop?)
								(obj cel?)
								(obj x?)
								(obj y?)
								(obj z?)
								(obj heading?)
								(obj priority?)
								(obj signal?)
							)
							(Print
								window: SysWindow
								addIcon: (obj view?) (obj loop?) (obj cel?) 0 0
								addText: @str 0 (+ temp215 5)
								init:
							)
							(= i (cast next: i))
						)
						(Print window: systemWindow)
					)
					(`@b
						(= str 0)
						(GetInput @str 4 {Flag No?})
						(= i (ReadNumber @str))
						(cond 
							((UseFlagObject)
								(if (flagObject test: i)
									(Prints {TRUE})
								else
									(Prints {FALSE})
								)
							)
							((Btst i) (Prints {TRUE}))
							(else (Prints {FALSE}))
						)
					)
					(`@c
						(Show CMAP)
					)
					(`@e
						(= userAlterEgo
							(if gTheNewDButton_2Value else (User alterEgo?))
						)
						(Printf
							{name: %s\nview: %d\nloop: %d\ncel: %d\nposn: %d %d %d\nheading: %d\npri: %d\nsignal: $%x\nillBits: $%x\nonControl: $%x\norigin on: $%x_}
							(userAlterEgo name?)
							(userAlterEgo view?)
							(userAlterEgo loop?)
							(userAlterEgo cel?)
							(userAlterEgo x?)
							(userAlterEgo y?)
							(userAlterEgo z?)
							(userAlterEgo heading?)
							(userAlterEgo priority?)
							(userAlterEgo signal?)
							(if (userAlterEgo respondsTo: #onControl)
								(userAlterEgo illegalBits?)
							else
								-1
							)
							(if (userAlterEgo respondsTo: #onControl)
								(userAlterEgo onControl:)
							else
								-1
							)
							(if (userAlterEgo respondsTo: #onControl)
								(userAlterEgo onControl: origin)
							else
								-1
							)
							79
							(userAlterEgo view?)
							(userAlterEgo loop?)
							(userAlterEgo cel?)
						)
					)
					(`@f
						(features eachElementDo: #perform showFeatureCode)
						(NameFeatureCode init:)
					)
					(`@g
						(= str 0)
						(GetInput @str 4 {Global No?})
						(= i (ReadNumber @str))
						(= str 0)
						(if (>= [ego i] 1000)
							(Printf
								{Global %d's value is $%x.}
								i
								[ego i]
							)
						else
							(Printf
								{Global %d's value is %d.}
								i
								[ego i]
							)
						)
						(if (GetInput @str 4 {New Value?})
							(= [ego i] (ReadNumber @str))
						)
					)
					(`@i
						(dInvD doit:)
					)
					(`@j
						((ScriptID LOGGER) doit: @sysLogPath 0)
					)
					(`@k
						(= str 0)
						(GetInput @str 4 {Flag No?})
						(= i (ReadNumber @str))
						(if (UseFlagObject)
							(flagObject set: i)
						else
							(Bset i)
						)
					)
					(`@l
						(= str 0)
						(GetInput @str 4 {Flag No?})
						(= i (ReadNumber @str))
						(if (UseFlagObject)
							(flagObject clear: i)
						else
							(Bclr i)
						)
					)
					(`@m
						(theGame showMem:)
					)
					(`@o
						(PolygonEditor doit:)
					)
					(`@p
						(Show PMAP)
					)
					(`@r
						(Printf
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
							#width 120
						)
						(theGame showMem:)
					)
					(`@s
						(dCastD doit:)
					)
					(`@t
						(if modelessDialog
							(modelessDialog dispose:)
						)
						(if (> (= i (GetNumber {Which room number?})) 0)
							(curRoom newRoom: i)
						)
					)
					(`@u
						(User canInput: TRUE canControl: TRUE)
						(theIconBar enable: 0 1 2 3 7 5 6)
					)
					(`@v
						(Show VMAP)
					)
					(`@w
						(CreateObject doit:)
					)
					(`@x
						(= quit TRUE)
					)
					(`@y
						(= savePort (GetPort))
						(SetPort 0)
						(= temp215 5)
						(= temp216 16)
						(= t 15)
						(= l 80)
						(= r (+ t (* 34 temp215)))
						(= b (+ l (* 10 temp216)))
						(= saveBits
							(Graph GSaveBits t l r b VMAP)
						)
						(Graph GFillRect t l r b 1 255)
						(= color 0)
						(while (< color 256)
							(Graph GFillRect
								(+ t temp215 (* temp215 (/ color 8)))
								(+ l temp216 (* 16 (mod color 8)))
								(+ t temp215 temp215 (* temp215 (/ color 8)))
								(+ l temp216 temp216 (* temp216 (mod color 8)))
								1
								color
							)
							(++ color)
						)
						(Graph GShowBits t l r b 1)
						(repeat
							(if
								(or
									(== ((= evt (Event new:)) type?) 1)
									(== (evt type?) 4)
								)
								(break)
							)
							(evt dispose:)
						)
						(evt dispose:)
						(Graph GRestoreBits saveBits)
						(Graph GShowBits t l r b VMAP)
						(SetPort savePort)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			(mouseDown
				(switch (event modifiers?)
					(13 0)
					(14 0)
					(12
						(event claimed: TRUE)
						(= savePort
							(Printf
								{%d/%d}
								(event x?)
								(event y?)
								64
								160
								10
								30
								999
								111
							)
						)
						(while (!= 2 ((= evt (Event new:)) type?))
							(evt dispose:)
						)
						(evt dispose:)
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
						(= userAlterEgo
							(if gTheNewDButton_2Value else (User alterEgo?))
						)
						(ego setMotion: 0)
						(while (!= 2 ((= evt (Event new:)) type?))
							(userAlterEgo
								x: (evt x?)
								y: (- (evt y?) 10)
								startUpd:
							)
							(Animate (cast elements?) FALSE)
							(evt dispose:)
						)
						(evt dispose:)
					)
				)
			)
		)
	)
)

(class NameFeatureCode of Code
	(properties)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(theDoits add: self)
	)
	
	(method (doit &tmp onMeAndLowYTheObj [str 40])
		(OnMeAndLowY init:)
		(features
			eachElementDo: #perform OnMeAndLowY (User curEvent?)
		)
		(if (= onMeAndLowYTheObj (OnMeAndLowY theObj?))
			(Format
				@str
				{%d, %d______%s}
				((User curEvent?) x?)
				((User curEvent?) y?)
				(onMeAndLowYTheObj name?)
			)
			(DrawStatus @str 67 0)
		)
	)
	
	(method (dispose)
		(DrawStatus 0)
		(DrawStatus {_} 0 0)
		(keyDownHandler delete: self)
		(theDoits delete: self)
	)
	
	(method (handleEvent event &tmp [temp0 50])
		(if
			(and
				(== (event type?) keyDown)
				(== (event message?) ESC)
			)
			(event claimed: 1)
			(self dispose:)
		)
	)
)

(instance showFeatureCode of Code
	(properties)
	
	(method (doit obj &tmp t l r b)
		(= t (obj nsTop?))
		(= l (obj nsLeft?))
		(= b (obj nsBottom?))
		(= r (obj nsRight?))
		(Graph GDrawLine t l t r 1 8)
		(Graph GDrawLine b l b r 1 8)
		(Graph GDrawLine t l b l 1 8)
		(Graph GDrawLine t r b r 1 8)
		(Graph GShowBits t l (+ b 1) (+ r 1) VMAP)
	)
)

(instance dInvD of Dialog
	(properties)
	
	(method (init &tmp temp0 temp1 temp2 i newDText inventoryFirst temp6)
		(= temp2 (= temp0 (= temp1 4)))
		(= i 0)
		(= inventoryFirst (inventory first:))
		(while inventoryFirst
			(= temp6 (NodeValue inventoryFirst))
			(++ i)
			(if (temp6 isKindOf: InvItem)
				(self
					add:
						((= newDText (DText new:))
							value: temp6
							text: (temp6 name?)
							nsLeft: temp0
							nsTop: temp1
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
			)
			(if
			(< temp2 (- (newDText nsRight?) (newDText nsLeft?)))
				(= temp2 (- (newDText nsRight?) (newDText nsLeft?)))
			)
			(if
				(>
					(= temp1
						(+ temp1 (- (newDText nsBottom?) (newDText nsTop?)) 1)
					)
					130
				)
				(= temp1 4)
				(= temp0 (+ temp0 temp2 10))
				(= temp2 0)
			)
			(= inventoryFirst (inventory next: inventoryFirst))
		)
		(= window systemWindow)
		(self setSize:)
		(= newDButton (DButton new:))
		(newDButton
			text: {I want it ALL!}
			setSize:
			moveTo: (- nsRight (+ 4 (newDButton nsRight?))) nsBottom
		)
		(newDButton
			move: (- (newDButton nsLeft?) (newDButton nsRight?)) 0
		)
		(self add: newDButton setSize: center:)
		(self setSize:)
		(= newDButton_2 (DButton new:))
		(newDButton_2
			text: {Outta here!}
			setSize:
			moveTo: (- nsRight (+ 4 (newDButton_2 nsRight?))) nsBottom
		)
		(newDButton_2
			move: (- (newDButton_2 nsLeft?) (newDButton_2 nsRight?)) 0
		)
		(self add: newDButton_2 setSize: center:)
		(return i)
	)
	
	(method (doit &tmp temp0 temp1)
		(asm
			pushi    #init
			pushi    0
			self     4
			pushi    #open
			pushi    2
			pushi    4
			pushi    15
			self     8
			lal      newDButton_2
			sat      temp0
code_0b8a:
			pushi    #doit
			pushi    1
			lst      temp0
			super    Dialog,  6
			sat      temp0
			not     
			bt       code_0bac
			lst      temp0
			ldi      65535
			eq?     
			bt       code_0bac
			lst      temp0
			lal      newDButton
			eq?     
			bt       code_0bac
			lst      temp0
			lal      newDButton_2
			eq?     
			bnt      code_0bae
code_0bac:
			jmp      code_0bc9
code_0bae:
			pushi    #get
			pushi    1
			pushi    #indexOf
			pushi    1
			pushi    #value
			pushi    0
			lat      temp0
			send     4
			push    
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			jmp      code_0b8a
code_0bc9:
			lst      temp0
			lal      newDButton
			eq?     
			bnt      code_0c01
			ldi      0
			sat      temp1
code_0bd4:
			lst      temp1
			pushi    #size
			pushi    0
			lag      inventory
			send     4
			lt?     
			bnt      code_0c01
			pushi    #isKindOf
			pushi    1
			class    132
			push    
			pushi    #at
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			send     6
			bnt      code_0bfd
			pushi    #get
			pushi    1
			lst      temp1
			lag      ego
			send     6
code_0bfd:
			+at      temp1
			jmp      code_0bd4
code_0c01:
			pushi    #eachElementDo
			pushi    2
			pushi    111
			pushi    1
			pushi    111
			pushi    0
			self     12
			ret     
		)
	)
	
	(method (handleEvent event &tmp eventMessage eventType)
		(= eventMessage (event message?))
		(switch (= eventType (event type?))
			(4
				(switch eventMessage
					(KEY_UP (= eventMessage 3840))
					(KEY_NUMPAD2 (= eventMessage 9))
				)
			)
			(64
				(switch eventMessage
					(JOY_UP
						(= eventMessage 3840)
						(= eventType 4)
					)
					(JOY_DOWN
						(= eventMessage 9)
						(= eventType 4)
					)
				)
			)
		)
		(event type: eventType message: eventMessage)
		(super handleEvent: event)
	)
)

(instance dCastD of Dialog
	(properties)
	
	(method (init &tmp temp0 temp1 temp2 temp3 newDText i temp6)
		(= temp2 (= temp0 (= temp1 4)))
		(= temp3 0)
		(= i (cast first:))
		(while i
			(= temp6 (NodeValue i))
			(++ temp3)
			(self
				add:
					((= newDText (DText new:))
						value: temp6
						text: (temp6 name?)
						nsLeft: temp0
						nsTop: temp1
						state: 3
						font: smallFont
						setSize:
						yourself:
					)
			)
			(if
			(< temp2 (- (newDText nsRight?) (newDText nsLeft?)))
				(= temp2 (- (newDText nsRight?) (newDText nsLeft?)))
			)
			(if
				(>
					(= temp1
						(+ temp1 (- (newDText nsBottom?) (newDText nsTop?)) 1)
					)
					100
				)
				(= temp1 4)
				(= temp0 (+ temp0 temp2 10))
				(= temp2 0)
			)
			(= i (cast next: i))
		)
		(= window systemWindow)
		(self setSize:)
		(= newDButton_2 (DButton new:))
		(newDButton_2
			text: {exit}
			setSize:
			moveTo: (- nsRight (+ 4 (newDButton_2 nsRight?))) nsBottom
		)
		(newDButton_2
			move: (- (newDButton_2 nsLeft?) (newDButton_2 nsRight?)) 0
		)
		(self add: newDButton_2 setSize: center:)
		(return temp3)
	)
	
	(method (doit &tmp theNewDButton_2 temp1)
		(self init:)
		(self open: 4 15)
		(= theNewDButton_2 newDButton_2)
		(repeat
			(if
				(or
					(not (= theNewDButton_2 (super doit: theNewDButton_2)))
					(== theNewDButton_2 -1)
					(== theNewDButton_2 newDButton_2)
				)
				(break)
			)
			(= gTheNewDButton_2Value (theNewDButton_2 value?))
		)
		(self eachElementDo: #dispose 1 dispose:)
	)
	
	(method (handleEvent event &tmp eventMessage eventType)
		(= eventMessage (event message?))
		(switch (= eventType (event type?))
			(4
				(switch eventMessage
					(KEY_UP (= eventMessage 3840))
					(KEY_NUMPAD2 (= eventMessage 9))
				)
			)
			(64
				(switch eventMessage
					(JOY_UP
						(= eventMessage 3840)
						(= eventType 4)
					)
					(JOY_DOWN
						(= eventMessage 9)
						(= eventType 4)
					)
				)
			)
		)
		(event type: eventType message: eventMessage)
		(super handleEvent: event)
	)
)
