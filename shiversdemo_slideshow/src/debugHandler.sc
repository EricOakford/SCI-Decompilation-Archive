;;; Sierra Script 1.0 - (do not remove this comment)
(script# 940)
(include game.sh)
(use Main)
(use Procs)
(use Intrface)
(use DText)
(use Plane)
(use String)
(use Array)
(use Print)
(use Polygon)
(use Feature)
(use Sound)
(use File)
(use Actor)
(use System)

(public
	debugHandler 0
	WhereTo 1
	proc940_2 2
	ShowApproach 3
)

(local
	newDText
	local1
	newList
	local3
	local4
)
(procedure (WhereTo &tmp temp0 roomNum str [temp3 2] newPrint [temp6 2])
	(= str (String newWith: 10 {}))
	((= newPrint (Print new:))
		font: smallFont
		fore: 255
		back: 0
		addText: {Where to?}
		addEdit: str 5 50 0
	)
	(= roomNum (newPrint init:))
	(= roomNum (str asInteger:))
	(curRoom newRoom: roomNum)
	(theGame handsOn:)
)

(procedure (proc940_2 &tmp i temp1)
	(for ((= i 1)) (<= i 15) ((++ i))
		(if [gNewSet i]
			(while ([gNewSet i] size:)
				(DeleteLine
					(= temp1 ([gNewSet i] at: 0))
					(cast plane?)
				)
				([gNewSet i] delete: temp1)
			)
			(= [gNewSet i] 0)
		)
	)
	(UpdatePlane (cast plane?))
	(FrameOut)
	(= global317 -1)
)

(procedure (ShowApproach)
	(cast eachElementDo: #perform showApproach)
	(features eachElementDo: #perform showApproach)
)

(procedure (PlaceEntity &tmp str what where)
	(= str (String newWith: 75 {}))
	(= what
		(Print
			font: smallFont
			y: 20
			addText: {Which entity?}
			addButton: 0 { water_} 0 10
			addButton: 1 {wax} 0 20
			addButton: 2 {ash} 0 30
			addButton: 3 {tar} 0 40
			addButton: 4 {fabric} 0 50
			addButton: 5 {wood} 0 60
			addButton: 6 {crystal} 0 70
			addButton: 7 {electric} 0 80
			addButton: 8 {sand} 0 90
			addButton: 9 {metal} 0 100
			addButton: -1 {cancel} 0 110
			init:
		)
	)
	(= where -1)
	(if (>= what 0)
		(= where [global160 (* 2 what)])
	)
	(switch what
		(0
			(= where
				(Print
					font: smallFont
					y: 60
					addTextF: {water is in %d} where
					addButton: 3000 { lake_} 0 10
					addButton: 9000 {main hall} 0 20
					addButton: 25000 {closet} 0 30
					addButton: 0 {none} 0 40
					addButton: -1 {cancel} 0 50
					init:
				)
			)
		)
		(1
			(= where
				(Print
					font: smallFont
					y: 60
					addTextF: {wax is in %d} where
					addButton: 8000 { library_} 0 10
					addButton: 22000 {shaman} 0 20
					addButton: 24000 {myths} 0 30
					addButton: 0 {none} 0 40
					addButton: -1 {cancel} 0 50
					init:
				)
			)
		)
		(2
			(= where
				(Print
					font: smallFont
					y: 60
					addTextF: {ash is in %d} where
					addButton: 6000 { office_} 0 10
					addButton: 21000 {funeral} 0 20
					addButton: 0 {none} 0 30
					addButton: -1 {cancel} 0 40
					init:
				)
			)
		)
		(3
			(= where
				(Print
					font: smallFont
					y: 60
					addTextF: {tar is in %d} where
					addButton: 11000 { strange beasts_} 0 10
					addButton: 14000 {subterranean} 0 20
					addButton: 0 {none} 0 30
					addButton: -1 {cancel} 0 40
					init:
				)
			)
		)
		(4
			(= where
				(Print
					font: smallFont
					y: 60
					addTextF: {fabric is in %d} where
					addButton: 20000 { tombs_} 0 10
					addButton: 21000 {funeral} 0 20
					addButton: 25000 {closet} 0 30
					addButton: 0 {none} 0 40
					addButton: -1 {cancel} 0 50
					init:
				)
			)
		)
		(5
			(= where
				(Print
					font: smallFont
					y: 60
					addTextF: {wood is in %d} where
					addButton: 7000 { workshop_} 0 10
					addButton: 23000 {gods} 0 20
					addButton: 24000 {myths} 0 30
					addButton: -29536 {2nd flr. pass.} 0 40
					addButton: 0 {none} 0 50
					addButton: -1 {cancel} 0 60
					init:
				)
			)
		)
		(6
			(= where
				(Print
					font: smallFont
					y: 60
					addTextF: {crystal is in %d} where
					addButton: 9000 { main hall_} 0 10
					addButton: 12000 {mysteries of the deep} 0 20
					addButton: 0 {none} 0 30
					addButton: -1 {cancel} 0 40
					init:
				)
			)
		)
		(7
			(= where
				(Print
					font: smallFont
					y: 60
					addTextF: {electric is in %d} where
					addButton: 29000 { planetarium_} 0 10
					addButton: 32000 {man's inhumanity} 0 20
					addButton: -26536 {mechanical} 0 30
					addButton: 0 {none} 0 40
					addButton: -1 {cancel} 0 50
					init:
				)
			)
		)
		(8
			(= where
				(Print
					font: smallFont
					y: 60
					addTextF: {sand is in %d} where
					addButton: 19000 { plants_} 0 10
					addButton: 12000 {mysteries of the deep} 0 20
					addButton: 0 {none} 0 30
					addButton: -1 {cancel} 0 40
					init:
				)
			)
		)
		(9
			(= where
				(Print
					font: smallFont
					y: 60
					addTextF: {metal is in %d} where
					addButton: 11000 { strange beasts_} 0 10
					addButton: 17000 {projection} 0 20
					addButton: -28536 {bedroom} 0 30
					addButton: 0 {none} 0 40
					addButton: -1 {cancel} 0 50
					init:
				)
			)
		)
	)
	(if (!= where -1) (= [global160 (* 2 what)] where))
)

(procedure (localproc_0ef5 param1)
	(DeletePolygon param1 thePlane)
)

(procedure (localproc_0f6e param1 &tmp i temp1 newIntArray temp3)
	(= i 0)
	(while (<= i (- (features size:) 1))
		(if ((features at: i) onMeCheck?)
			(if
			(((features at: i) onMeCheck?) isKindOf: Polygon)
				(= newIntArray (IntArray new:))
				(= temp3
					(-
						((((features at: i) onMeCheck?) points?) size:)
						1
					)
				)
				(= temp1 0)
				(while (<= temp1 temp3)
					(newIntArray
						at: temp1 ((((features at: i) onMeCheck?) points?) at: temp1)
					)
					(++ temp1)
				)
				(param1
					add:
						((Polygon new:)
							size: (/ (+ temp3 1) 2)
							points: newIntArray
							dynamic: 1
							yourself:
						)
				)
			else
				(Prints
					{A feature in this room cannot be displayed due to it being a list of polygons (and that part in debug hasn't been coded yet).}
				)
			)
		else
			(param1
				add:
					((Polygon new:)
						init:
							((features at: i) nsLeft?)
							((features at: i) nsTop?)
							((features at: i) nsRight?)
							((features at: i) nsTop?)
							((features at: i) nsRight?)
							((features at: i) nsBottom?)
							((features at: i) nsLeft?)
							((features at: i) nsBottom?)
						yourself:
					)
			)
		)
		(++ i)
	)
	(AddPolygon thePlane param1 250 255 0 0 1 1)
)

(instance debugSound of Sound)

(instance debugHandler of Feature
	(properties
		y -1
	)
	
	(method (init)
		(super init:)
		(self y: -1)
		(= local1 0)
		(= global338 0)
		(= newList 0)
		(= newDText 0)
		(= local3 0)
		(= local4 0)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
	)
	
	(method (dispose &tmp temp0)
		(if local1
			(DeletePolygon local1 thePlane)
			(= local1 0)
		)
		(if global338
			(DeletePolygon global338 thePlane)
			(= global338 0)
		)
		(if newDText
			(newDText dispose:)
			(= newDText 0)
			(= local4 0)
		)
		(if newList (newList dispose:) (= newList 0))
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp str temp1 newEvent temp3 newStrAsInteger_2 [temp5 5] temp10 [temp11 4] newStrAsInteger [temp16 13] temp29 temp30 [temp31 7])
		(if (event claimed?) (return))
		(= str (String new:))
		(switch (event type?)
			(keyDown
				(event claimed: 1)
				(switch (event message?)
					(`?
						(Prints
							{ ALT-C - show Cursor coordinates\n
							CTRL-F - show Features\n
							ALT-F - Flag set/clear\n
							ALT-G - set Global variables\n
							ALT-H - Help\n
							ALT-I - get Inventory item\n
							ALT-M - show Memory\n
							ALT-P - show Palette\n
							CTRL-P - load Picture\n
							ALT-R - show Room info\n
							CTRL-R - force purge, show memory\n
							ALT-S - test Sound\n
							CTRL-S - stop all sounds\n
							ALT-T - Teleport\n
							CTRL-T - show priority Map\n
							ALT-W - show polygons\n
							ALT-X - Quit\n
							CTRL-D - Toggle Entity Debugging\n
							CTRL-E - Place Entity_}
						)
					)
					($0001
						(proc951_2 10)
					)
					(`+
						(MonoOut {+score})
						(proc951_15 (Random 1 1000))
					)
					(`-
						(MonoOut {-score})
						(proc951_15 (Random -1000 -1))
					)
					($001a
						(proc951_2 -10)
					)
					(`@a)
					(`@c
						(Print
							font: smallFont
							addTextF: {Cur X: %d,Y: %d} (event x?) (event y?)
							init:
						)
					)
					(`@d
						(if (== global182 1)
							(= global182 0)
						else
							(= global182 1)
						)
					)
					($0004
						(if (proc951_5 45) (proc951_4 45) else (proc951_3 45))
					)
					(`^e (PlaceEntity))
					($0006
						(if (!= global317 -1)
							(if newDText
								(newDText dispose:)
								(= newDText 0)
								(= local4 0)
							)
							(localproc_0ef5 global338)
							(= global338 0)
							(= newList 0)
							(proc940_2)
						else
							(if (features size:)
								(if newList (newList dispose:))
								(= newList (List new:))
								(= global338 (localproc_0f6e newList))
								(= global317 1)
							)
							(ShowApproach)
						)
					)
					(`@f
						(= str (String newWith: 75 {}))
						(Print
							font: userFont
							y: 60
							addText: {Flag num?}
							addEdit: str 5 60
							init:
						)
						(= newStrAsInteger (str asInteger:))
						(switch
							(Print
								font: userFont
								y: 60
								addTextF:
									(if (proc951_5 newStrAsInteger)
										{flag %d is SET}
									else
										{flag %d is CLEARED}
									)
									newStrAsInteger
								addButton: 1 { set_} 0 15
								addButton: 2 {clear} 0 35
								addButton: -1 {cancel} 0 55
								init:
							)
							(1 (proc951_3 newStrAsInteger))
							(2 (proc951_4 newStrAsInteger))
						)
					)
					(`@g
						(GetInput str 5 {Variable No.})
						(if (not (= newStrAsInteger_2 (str asInteger:)))
							(return)
						)
						(GetInput str 5 {Value})
						(= [ego newStrAsInteger_2] (str asInteger:))
					)
					(`@h
						(Print
							font: smallFont
							addText:
								{ ALT-C - show Cursor coordinates\n
								CTRL-F - show Features\n
								ALT-F - Flag set/clear\n
								ALT-G - set Global variables\n
								ALT-H - Help\n
								ALT-I - get Inventory item\n
								ALT-M - show Memory\n
								ALT-P - show Palette\n
								CTRL-P - load Picture\n
								ALT-R - show Room info\n
								CTRL-R - force purge, show memory\n
								ALT-S - test Sound\n
								CTRL-S - stop all sounds\n
								ALT-T - Teleport\n
								CTRL-T - show priority Map\n
								ALT-W - show polygons\n
								ALT-X - Quit\n
								CTRL-D - Toggle Entity Debugging\n
								CTRL-E - Place Entity_}
							init:
						)
					)
					(`@i)
					(TAB
						(GetInput str 5 {Intensify number?})
						(= newStrAsInteger_2 (str asInteger:))
						(Palette 2 95 235 newStrAsInteger_2)
					)
					(`@m (theGame showMem:))
					($0014
						(= temp29 (GetNumber {x:} (ego x?)))
						(= temp30 (GetNumber {y:} (ego y?)))
						(ego posn: temp29 temp30)
					)
					(`@p
						(PalDisplay doit: 90 90)
					)
					($0010
						(if
							(and
								(>
									(= newStrAsInteger_2
										(GetNumber {Pic #?} (curRoom picture?))
									)
									0
								)
								(ResCheck RES_PIC newStrAsInteger_2)
							)
							(curRoom picture: newStrAsInteger_2)
							(curRoom drawPic: newStrAsInteger_2)
							(FrameOut)
						else
							(Printf
								{Picture number %d not available}
								newStrAsInteger_2
							)
						)
					)
					(`@r
						(str
							format:
								{Current Room\n
								name: %s\n
								script: %s\n
								horizon: %d\n
								vanishingX: %d\n
								vanishingY: %d\n
								picAngle: %d\n
								north: %d\n
								south: %d\n
								east: %d\n
								west: %d\n
								style: %d\n
								curPic: %d\n
								robot: %d}
								(curRoom name?)
								(if (curRoom script?)
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
								(if autoRobot (autoRobot robot?) else 0)
						)
						(Print font: smallFont addText: str 0 0 init:)
					)
					($0012
						(Purge 20000)
						(theGame showMem:)
					)
					(`@s
						(= temp10 (GetNumber {which sound number?}))
						(= newStrAsInteger (GetNumber {setLoop?}))
						(sounds play: temp10 newStrAsInteger 122 0)
					)
					($0013 (sounds stopAll:))
					(`@t (WhereTo))
					(`@w
						(cond 
							(local1 0)
							((curRoom obstacles?)
								(= local1 (AddPolygon thePlane (curRoom obstacles?)))
								(FrameOut)
							)
							(else (Prints {Sorry, this room has no polygons.}))
						)
					)
					(`@v
						(Prints {Version number 0.08})
					)
					(`@x
						(= quit TRUE)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			(mouseDown
				(if (== (event modifiers?) altDown)
					(event claimed: TRUE)
					(while (!= mouseUp ((= newEvent (Event new:)) type?))
						((user alterEgo?)
							posn: (newEvent x?) (- (newEvent y?) 10)
							setMotion: 0
						)
						(newEvent dispose:)
					)
					(newEvent dispose:)
				)
			)
		)
		(str dispose:)
	)
)

(instance poly_pol of File
	(properties
		name "poly.pol"
	)
)

(instance ourPlane of Plane)

(instance PalDisplay of Code	
	(method (doit param1 param2 &tmp temp0 newView temp2 temp3 temp4 temp5 temp6)
		(= temp0
			((Plane new:)
				priority: (+ 1 (GetHighPlanePri))
				init: param1 param2 (+ 64 param1) (+ 64 param2)
				addCast: (Cast new:)
				yourself:
			)
		)
		(= newView (View new:))
		(= temp2 (Bitmap 0 64 64 0 254 320 200))
		(= temp4 (= temp3 0))
		(= temp5 (= temp6 3))
		(= temp4 0)
		(= temp6 3)
		(while (< temp6 64)
			(= temp3 0)
			(= temp5 3)
			(while (< temp5 64)
				(Bitmap
					5
					temp2
					temp3
					temp4
					temp5
					temp6
					(+ (/ temp3 4) (* 16 (/ temp4 4)))
				)
				(= temp3 (+ temp3 4))
				(= temp5 (+ temp5 4))
			)
			(= temp4 (+ temp4 4))
			(= temp6 (+ temp6 4))
		)
		(newView
			x: 0
			y: 0
			plane: temp0
			bitmap: temp2
			init: ((temp0 casts?) at: 0)
		)
	)
)

(instance showApproach of Code
	(method (doit param1 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8)
		(if [gNewSet (++ global317)] (proc940_2))
		(if
			(or
				(not
					(InRect
						27
						7
						289
						148
						(param1 approachX?)
						(param1 approachY?)
					)
				)
				(not global317)
				(== param1 ego)
			)
			(return 0)
		else
			(= temp7 1)
			(= [gNewSet global317] (Set new:))
			(= temp0 (- (param1 approachX?) 1))
			(= temp1 (param1 approachY?))
			(= temp2 (+ (param1 approachX?) 1))
			(= temp3 (- (param1 approachY?) 1))
			(= temp4 (param1 approachX?))
			(= temp5 (+ (param1 approachY?) 1))
			([gNewSet global317]
				add: (AddLine
					(cast plane?)
					temp0
					temp1
					temp2
					temp1
					255
					temp7
					0
					0
					1
				)
			)
			([gNewSet global317]
				add: (AddLine
					(cast plane?)
					temp4
					temp3
					temp4
					temp5
					255
					temp7
					0
					0
					1
				)
			)
		)
		(UpdatePlane (cast plane?))
		(return (FrameOut))
	)
)

(instance featureCheck of Code
	(method (doit &tmp temp0 temp1)
		((user curEvent?) localize: (cast plane?))
		(= temp0 ((user curEvent?) x?))
		(= temp1 ((user curEvent?) y?))
		(cond 
			((= local3 (features firstTrue: #onMe temp0 temp1))
				(if (!= local4 local3)
					(if newDText (newDText dispose:) (= newDText 0))
					((= newDText (DText new:))
						text: (KString 8 (local3 name?))
						fore: 255
						back: 0
						setSize: 240
						setPri: 255
					)
					(if (< temp0 145)
						(newDText posn: 188 30 init:)
					else
						(newDText posn: 65 30 init:)
					)
					(= local4 local3)
					(= local3 0)
				)
			)
			(newDText (newDText dispose:) (= newDText 0) (= local4 0))
		)
	)
)
