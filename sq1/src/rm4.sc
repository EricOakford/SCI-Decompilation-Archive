;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
(include game.sh)
(use Main)
(use Intrface)
(use Arcada)
(use SQRoom)
(use Elevator)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm4 0
	beginGame 1
)

(local
	local0
	inCloset
	panicDroidPassingBy
	local3
)
(instance rm4 of SQRoom
	(properties
		picture 4
		east 5
		west 3
	)
	
	(method (init &tmp [str 90])
		(LoadMany VIEW 104 4 400)
		(Load SOUND 311)
		(ego init: setStep: 4 2)
		(body init:)
		(if (== currentFloor 1)
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 0 0 319 0 319 52 162 78 121 78 103 69 103 48 99 48 99 68 82 77 0 77
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 187 94 319 72 319 189 0 189 0 94
						yourself:
					)
			)
			((ScriptID ARCADA 0)
				sarienEntryDir: 2
				s1startY: 54
				s2startY: 67
				s1gotoX: 229
				s1gotoY: 69
				s2gotoX: 231
				s2gotoY: 85
			)
		else
			(body approachVerbs: verbLook verbDo verbTalk)
			(if (== prevRoomNum 3) (ego setPri: 3))
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 0 156 60 156 66 152 112 152 118 158 187 187 319 187 319 189 0 189
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							0 0
							319 0
							319 160
							299 160
							297 172
							279 172
							276 156
							260 156
							260 172
							243 172
							233 163
							182 163
							103 133
							0 133
						yourself:
					)
			)
			((ScriptID ARCADA 0)
				sarienEntryDir: 1
				s1startY: 138
				s2startY: 154
				s1gotoX: 88
				s1gotoY: 140
				s2gotoX: 66
				s2gotoY: 155
			)
		)
		(self setRegions: ARCADA)
		((ScriptID ARCADA 0) safeCode: egoSafe)
		(alertSign init: setCycle: Forward)
		(switch prevRoomNum
			(3 (= style 12))
			(5 (= style 11))
			(8
				(ego posn: 270 164)
				(= style 10)
			)
			(else 
				(= style -32759)
				(LoadMany SOUND 998 983 309 357)
				(LoadMany VIEW 414)
				(ego loop: 2 posn: 100 65 heading: 180)
				(if (not howFast)
					(= selfDestructTimer 1801)
				else
					(= selfDestructTimer 901)
				)
				(alertSign cel: 0 setCycle: 0)
				(self setScript: beginGame)
			)
		)
		(closetDoor init: stopUpd:)
		(= local0 0)
		(super init:)
		(lowerDoor
			exiting: (== prevRoomNum 8)
			light: elevatorLight
			init:
			whereTo: 8
		)
		(if (OneOf prevRoomNum west east) (HandsOn))
	)
	
	(method (doit)
		(super doit:)
		(if (== currentFloor 2)
			(if (< (ego x?) 186)
				(ego setPri: 3)
			else
				(ego setPri: -1)
			)
		)
		(cond 
			(script 0)
			((and inCloset (ego mover?)) (self setScript: walkOutCloset))
			((lowerDoor inFront:) (lowerDoor open:))
			(
			(and (& (ego onControl: 0) cBLUE) (not inCloset)) (= inCloset 1))
			(
				(and
					(ego inRect: 50 40 150 99)
					(& (ego onControl: origin) cYELLOW)
				)
				(self setScript: walkInCloset)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(User controls?)
				inCloset
				(== (theIconBar curIcon?) (theIconBar at: ICON_WALK))
				(or
					(== (event message?) dirN)
					(& (event type?) direction)
				)
			)
			(self setScript: walkOutCloset)
			(event claimed: TRUE)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if inCloset
					(Print
						{That's not possible. It's so dark that you couldn't find your own navel lint. Surely you MUST remember that this was the criteria by which you came to choose this location for your nap... er... work station.}
					)
				else
					(Print 4 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (notify)
		(if (== prevRoomNum 8) (HandsOff))
	)
)

(instance searchBody of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setHeading: 180 self)
			)
			(1
				(if register (Print 4 1 (= state 2)))
				(ego
					view: 4
					loop: (if (< (ego x?) ((CueObj client?) x?)) 1 else 0)
					cycleSpeed: 5
					moveSpeed: 5
					cel: register
					setCycle: (if register BegLoop else EndLoop) self
				)
			)
			(2
				(= state 0)
				(= register 3)
				(ego
					loop: (+ (ego loop?) 2)
					cel: 0
					setCycle: Oscillate 2 self
				)
			)
			(3
				(HandsOn)
				(= state -1)
				(= register 0)
				(NormalEgo 2 0 60)
				(self dispose:)
			)
		)
	)
)

(instance walkInCloset of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(soundFx number: 311 loop: 1 play:)
				(closetDoor startUpd: setCycle: EndLoop self)
			)
			(1
				(soundFx stop:)
				(ego setMotion: PolyPath 101 54 self)
			)
			(2
				(= inCloset 1)
				(soundFx number: 311 loop: 1 play:)
				(closetDoor stopUpd: setCycle: BegLoop self)
			)
			(3
				(soundFx stop:)
				(if (not (ArcadaCheck #rFlag1 rFWalkedInCloset))
					(soundFx number: 310 loop: 1 play: self)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(4
				(Print 4 2)
				((ScriptID ARCADA 0)
					rFlag1: (| ((ScriptID ARCADA 0) rFlag1?) rFWalkedInCloset)
				)
				(= cycles 6)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance walkOutCloset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(soundFx number: 311 loop: 1 play:)
				(closetDoor startUpd: setCycle: EndLoop self)
			)
			(1
				(soundFx stop:)
				(ego setMotion: MoveTo (ego x?) 78 self)
			)
			(2
				(= inCloset 0)
				(soundFx number: 311 loop: 1 play:)
				(closetDoor stopUpd: setCycle: BegLoop self)
			)
			(3
				(soundFx stop:)
				(if (not (client isKindOf: Script)) (HandsOn))
				(self dispose:)
			)
		)
	)
)

(instance beginGame of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if panicDroidPassingBy
			(if (ego moveHead?) (ego moveHead: 0))
			(cond 
				(
					(and
						(>=
							220
							(= temp0
								(GetAngle
									(ego x?)
									(ego y?)
									(panicDroid x?)
									(panicDroid y?)
								)
							)
						)
						(>= temp0 135)
					)
					((ego _head?) cel: 2)
				)
				((< temp0 135) ((ego _head?) cel: 4))
				(else ((ego _head?) cel: 5))
			)
		)
		(if
		(and (not panicDroidPassingBy) (not (ego moveHead?)))
			(ego moveHead: 1)
		)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState &tmp [str 150])
		(switch (= state newState)
			(0
				(theIconBar enable:)
				(HandsOff)
				((ScriptID ARCADA 0) cartNumber: (Random 1 5))
				(= selfDestructCode
					(+
						(* (Random 1 9) 1000)
						(* (Random 1 9) 100)
						(* (Random 1 9) 10)
						(* (Random 1 9) 1)
					)
				)
				(= cycles 12)
			)
			(1
				(theMusic number: 307 loop: -1 flags: mNOPAUSE play:)
				(alertSign setCycle: Forward)
				(= currentFloor 1)
				(= inCloset 1)
				(= seconds 3)
			)
			(2
				(self setScript: walkOutCloset self)
			)
			(3 (= seconds 2))
			(4
				(if
				(< (theGame detailLevel:) (alertSign detailLevel:))
					(alertSign setCycle: 0)
				)
				(ShakeScreen 3 (Random 1 3))
				(theMusic number: 339 loop: 1 play: self)
			)
			(5
				(= panicDroidPassingBy TRUE)
				(HandsOn)
				(theIconBar disable:
					ICON_WALK
					ICON_DO
					ICON_TALK
					ICON_SMELL
					ICON_TASTE
					ICON_ITEM
					ICON_INVENTORY
				)
				(panicDroid init: setCycle: Forward)
				(theMusic number: 300 loop: -1 play:)
			)
			(6
				(HandsOff)
				(theIconBar curIcon: (theIconBar at: ICON_WALK))
				(= panicDroidPassingBy FALSE)
				(Print 4 3)
				(= cycles 3)
			)
			(7
				(Format @str 4 4 (/ selfDestructTimer 60))
				(Print @str #mode teJustCenter #at 123 24 #dispose)
				(theMusic2 number: 309 loop: 1 play: self)
			)
			(8
				(= seconds 0)
				((ScriptID ARCADA 0)
					inGame: TRUE
					rFlag1: (| ((ScriptID ARCADA 0) rFlag1?) rFStartedGame)
				)
			)
			(9
				(if modelessDialog
					(modelessDialog caller: self seconds: 10)
				)
				(= register 1)
				0
			)
			(10
				(= register 0)
				(= cycles 1)
			)
			(11 (HandsOn) (self dispose:))
		)
	)
	
	(method (handleEvent event)
		(if (and register (event type?))
			(if modelessDialog
				(modelessDialog seconds: 0 caller: 0 dispose:)
			)
			(= register 0)
			(= cycles 1)
			(event claimed: 1)
		)
	)
)

(instance egoSafe of Code
	(properties)
	
	(method (doit)
		(return
			(if
			(or (lowerDoor busy?) (!= currentFloor sarienFloor))
			else
				(& (ego onControl: origin) (| cGREEN cBLUE))
			)
		)
	)
)

(instance elevatorLight of View
	(properties
		description {elevator light}
		sightAngle 45
		view 104
		loop 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(switch theVerb
				(verbLook
					(Print 4 5)
				)
				(verbSmell
					(Print 4 6)
				)
				(verbTaste
					(Print 4 6)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print {You aren't on that level.})
		)
	)
)

(instance body of View
	(properties
		x 88
		y 158
		description {body}
		sightAngle 45
		approachX 97
		approachY 153
		view 400
		priority 14
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb theItem)
		(if (== currentFloor 2)
			(switch theVerb
				(verbLook
					(Print 4 7)
				)
				(verbDo
					(cond 
						(inCloset (Print 4 8))
						((!= currentFloor 2) (Print 4 9))
						(else (curRoom setScript: searchBody))
					)
				)
				(verbTalk
					(Print 4 10)
				)
				(verbSmell
					(Print 4 11)
				)
				(verbTaste
					(Print 4 12)
				)
				(verbUse
					(switch theItem
						(iBuckazoid
							(Print 4 13)
						)
						(iCartridge
							(Print 4 14)
						)
						(iKeyCard
							(Print 4 15)
						)
						(iWidget
							(Print 4 16)
						)
						(iGadget
							(Print 4 17)
						)
						(else 
							(super doVerb: theVerb theItem)
						)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print {You aren't on that level.})
		)
	)
)

(instance lowerDoor of Elevator
	(properties
		x 269
		y 167
		description {elevator door}
		sightAngle 45
		view 104
		loop 1
		cycleSpeed 4
		level 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 2)
			(switch theVerb
				(verbLook
					(Print 4 18)
				)
				(verbSmell
					(Print 4 19)
				)
				(verbTaste
					(Print 4 20)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print {You aren't on that level.})
		)
	)
)

(instance closetDoor of Prop
	(properties
		x 101
		y 68
		description {closet}
		sightAngle 90
		view 104
		priority 3
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(switch theVerb
				(verbLook
					(if inCloset
						(Print 4 21)
					else
						(Print 4 22)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print {You aren't on that level.})
		)
	)
)

(instance alertSign of Prop
	(properties
		x 40
		y 46
		description {warning light}
		sightAngle 90
		view 104
		loop 3
		cycleSpeed 8
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== currentFloor 1)
			(switch theVerb
				(verbLook
					(if inCloset
						(Print 4 21)
					else
						(Print 4 23)
					)
				)
				(verbDo
					(Print 4 24)
				)
				(verbSmell
					(Print 4 25)
				)
				(verbTaste
					(Print 4 26)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		else
			(Print {You aren't on that level.})
		)
	)
)

(instance panicDroid of Actor
	(properties
		x 337
		y 57
		description {panicking droid}
		lookStr {A somewhat spastic research droid blows by in a tizz. Perhaps you could provide some relaxation therapy instruction to reduce its level of tension.}
		yStep 6
		view 414
		cycleSpeed 5
		xStep 10
		moveSpeed 4
	)
	
	(method (init)
		(super init: &rest)
		(theMusic2 number: 357 loop: -1 play: 0)
		(self
			setLoop: 0
			ignoreActors: 1
			setMotion: DPath 159 89 -27 89 self
		)
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(super doit:)
		(if
		(< (= temp0 (Abs (- (panicDroid x?) (ego x?)))) 0)
			(= temp0 0)
		)
		(if (> temp0 90) (= temp0 90))
		(if
			(>
				(= temp1
					(Abs (- (+ (- (ego x?) (panicDroid x?)) 63) 127))
				)
				127
			)
			(= temp1 127)
		)
		(if (< temp1 0) (= temp1 0))
		(theMusic2
			send: 7 10 temp1
			send: 8 10 temp1
			send: 9 10 temp1
			send: 11 10 temp1
			setVol: (- 127 temp0)
		)
		(if (< (- x (ego x?)) 0)
			(if (< (= local3 (- local3 6)) -512) (= local3 -512))
			(theMusic2
				send: 7 224 local3
				send: 8 224 local3
				send: 9 224 local3
				send: 11 224 local3
			)
		)
	)
	
	(method (dispose)
		(theMusic2 loop: 0 stop:)
		(super dispose:)
	)
	
	(method (cue)
		(beginGame cue:)
		(self dispose:)
	)
)
