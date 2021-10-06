;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include game.sh)
(use Main)
(use Intrface)
(use Chase)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm43 0
)

(local
	local0
	termMouth
	egoMouth
	eye
	local4
	hat
	newSound
)
(class BView of View
	(method (delete)
		(&= signal $ffdf)
		(super delete:)
	)
)

(instance footPrint of BView)

(instance rm43 of Room
	(properties
		picture 43
		style IRISOUT
		east 44
		south 47
		west 42
	)
	
	(method (init)
		(User canInput: TRUE canControl: FALSE)
		(NormalEgo)
		(if (not visitedPhleebhutStore)
			(Load VIEW 66)
			(Load VIEW 67)
		)
		(Load VIEW 70)
		(Load VIEW 121)
		(Load SOUND 13)
		(super init:)
		(w init:)
		(orld init: addToPic:)
		(o init: addToPic:)
		(wonder init: addToPic:)
		(if (or (== terminatorState terminatorMEET) (== terminatorState terminatorSEARCHING))
			(ego observeControl: cYELLOW)
			(door
				view: 121
				loop: 0
				cel: 0
				posn: 157 118
				init:
				stopUpd:
			)
		)
		(switch terminatorState
			(terminatorMEET
				(if (== (Random 1 3) 2)
					((= terminator (Actor new:)) posn: 1000 1000 init:)
					(= notifyCountdown (Random 2 10))
				)
			)
			(terminatorSEARCHING
				((= terminator (Actor new:)) posn: 1000 1000 init:)
				(= notifyCountdown 3)
			)
		)
		(if (cast contains: terminator)
			(terminator
				view: 106
				setCycle: Walk
				observeControl: 2
				posn: 1000 1000
				hide:
				init:
			)
			(switch prevRoomNum
				(east
					(terminator posn: 359 140)
				)
				(west
					(terminator posn: -40 140)
				)
				(south
					(terminator posn: 160 249)
				)
			)
			(= terminatorState terminatorMEET)
			(self setScript: tActions)
		)
		(if (not visitedPhleebhutStore)
			(car init:)
			(shadow init:)
			(HandsOff)
			(self setScript: kid1Script)
		else
			(if (!= (theMusic number?) 22)
				(theMusic number: 22 priority: 1 loop: -1 play:)
			)
			(ego view: 0 setStep: 3 2 illegalBits: -32768 init:)
			(switch prevRoomNum
				(42 (ego posn: 2 (ego y?)))
				(44
					(if (< (ego y?) 110)
						(ego posn: 317 110)
					else
						(ego posn: 317 (ego y?))
					)
				)
				(47 (ego posn: (ego x?) 187))
				(470
					(ego
						view: (if wearingChickenHat 138 else 0)
						posn: 158 125
						loop: 2
						cel: 0
					)
					(if wearingChickenHat (Load VIEW 755))
					(cond 
						((== terminatorState 0)
							(HandsOff)
							(= terminatorState terminatorMEET)
							(self setScript: terminatorGrabs)
							(Load PICTURE 430)
							(Load VIEW 107)
							(Load VIEW 121)
						)
						(wearingChickenHat (self setScript: hatTrick))
					)
				)
				(else  (ego posn: 160 187))
			)
			(if (!= (curRoom script?) terminatorGrabs) (HandsOn))
		)
		(= local0 1)
	)
	
	(method (doit &tmp [temp0 30])
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if
			(or
				(== (ego onControl: 0) cGREEN)
				(== (ego onControl: 0) 5)
			)
			(self newRoom: 470)
		)
		(if (== (-- local0) 0)
			(if (== (w cel?) 0) (w cel: 1) else (w cel: 0))
			(= local0 (Random 1 4))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look/leg') (Print 43 0))
					((Said 'look/feet,s') (Print 43 1))
					(
						(or
							(Said 'devil,s')
							(Said '/devil,s')
							(Said '//devil,s')
						)
						(Print 43 2)
					)
					((Said 'look/door,entrance,door')
						(if (cast contains: door)
							(Print 43 3)
						else
							(Print 43 4)
						)
					)
					((Said 'knock/door')
						(if (cast contains: door)
							(if (< (ego distanceTo: door) 10)
								(Print 43 5)
							else
								(NotClose)
							)
						else
							(Print 43 6)
						)
					)
					((Said 'open/door')
						(if (cast contains: door)
							(if (< (ego distanceTo: door) 10)
								(Print 43 7)
							else
								(NotClose)
							)
						else
							(Print 43 6)
						)
					)
					((or (Said 'get/cap') (Said 'get<up/cap')) (if (ego has: 6) (Print 43 8) else (Print 43 9)))
					(
						(Said
							'look/box,table,glass,animal,animal,lawn,slime,display'
						)
						(if (ego inRect: 189 110 246 130)
							(Print 43 10)
						else
							(Print 43 11)
						)
					)
					((Said 'break/box,glass')
						(if (ego inRect: 189 110 246 130)
							(Print 43 12)
						else
							(NotClose)
						)
					)
					((Said 'open/box')
						(cond 
							((== (curRoom script?) tActions) (Print 43 13))
							((ego inRect: 189 110 246 130) (curRoom setScript: AlienDeath))
							(else (Print 43 14))
						)
					)
					((Said 'read,look/menace[<neon,flashing]')
						(if
						(and (cast contains: door) (ego inRect: 102 90 246 143))
							(Print 43 15)
						else
							(Print 43 16)
							(if (cast contains: door) (Print 43 17))
						)
					)
					(
						(or
							(Said 'look/area')
							(Said 'look/building,hill,animal')
							(Said 'look[<around][/!*]')
						)
						(Print 43 18)
					)
				)
			)
			(keyDown
				(if
					(and
						(== (event type?) keyDown)
						(== (event message?) ENTER)
						(== (curRoom script?) terminatorGrabs)
						(<= 5 (terminatorGrabs state?))
						(<= (terminatorGrabs state?) 8)
					)
					(cls)
					(event claimed: 1)
					(terminatorGrabs seconds: 0)
					(terminatorGrabs cue:)
				)
			)
		)
	)
	
	(method (newRoom n)
		(if (or (not script) (== script tActions))
			(ego illegalBits: cWHITE)
			(cond 
				((!= n 470)
					(theMusic owner: -1 number: 22 priority: 0 loop: -1 play:)
				)
				((== (theMusic number?) 22)
					(theMusic fade:)
				)
			)
			(if
				(and
					(cast contains: terminator)
					(< (ego distanceTo: terminator) 120)
				)
				(= terminatorState terminatorSEARCHING)
			)
			(DisposeScript EXTRA)
			(if (not isHandsOff)
				(super newRoom: n)
			)
		)
	)
)

(instance kid1Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 13 priority: 3 loop: -1 play:)
				(kid1 init: setMotion: MoveTo 188 137 self)
			)
			(1
				(car setScript: kid2Script)
				(kid1 setMotion: MoveTo 246 160 self)
			)
			(2
				(kid1 dispose:)
			)
		)
	)
)

(instance kid2Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(kid2 init: setMotion: MoveTo 188 137 self)
			)
			(1
				(curRoom setScript: momScript)
				(kid2 setMotion: MoveTo 246 160 self)
			)
			(2
				(kid2 dispose:)
			)
		)
	)
)

(instance momScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mom init: setMotion: MoveTo 197 142 self)
			)
			(1
				(mom setMotion: MoveTo 284 149 self)
			)
			(2
				(car setScript: popScript)
				(mom setMotion: MoveTo 284 160 self)
			)
			(3
				(mom dispose:)
			)
		)
	)
)

(instance popScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(pop init: setMotion: MoveTo 152 120 self)
			)
			(1
				(pop setMotion: MoveTo 196 146 self)
			)
			(2
				(pop loop: 4 cel: 0 setCycle: Walk)
				(= cycles 2)
			)
			(3
				(pballoon init:)
				(pop cycleSpeed: 1 setCycle: EndLoop self)
			)
			(4
				(pballoon dispose:)
				(pop
					loop: 3
					cycleSpeed: 0
					setCycle: Forward
					setMotion: MoveTo 284 160 self
				)
				(curRoom setScript: festerScript)
			)
			(5
				(pop dispose:)
			)
		)
	)
)

(instance festerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fester init: setMotion: MoveTo 185 137 self)
			)
			(1
				(fester loop: 1 setCycle: EndLoop self)
			)
			(2
				(fester loop: 4 setCycle: Forward)
				(fballoon init:)
				(ego setScript: carScript)
				(= seconds 4)
			)
			(3
				(fballoon cel: 1)
				(= seconds 4)
			)
			(4
				(fballoon dispose:)
				(fester loop: 1 cel: 2 setCycle: BegLoop self)
			)
			(5
				(fester
					loop: 2
					setCycle: Walk
					setMotion: MoveTo 141 108 self
				)
			)
			(6
				(fester dispose:)
				(ego init: view: 0 setStep: 3 2)
				(switch prevRoomNum
					(42
						(ego
							posn: -5 (ego y?)
							setMotion: MoveTo 5 (ego y?) self
						)
					)
					(44
						(if (< (ego y?) 110)
							(ego posn: 324 110)
						else
							(ego posn: 324 (ego y?))
						)
						(ego setMotion: MoveTo 314 (ego y?) self)
					)
					(47
						(ego
							posn: (ego x?) 222
							setMotion: MoveTo (ego x?) 186 self
						)
					)
					(else 
						(ego posn: 160 222 setMotion: MoveTo 166 186 self)
					)
				)
			)
			(7
				(HandsOn)
				(client setScript: 0)
				(= visitedPhleebhutStore 1)
			)
		)
	)
)

(instance carScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(car setMotion: MoveTo 278 160 self)
				(shadow setMotion: MoveTo 287 191)
			)
			(1
				(car xStep: 10 setMotion: MoveTo 368 160 self)
				(shadow xStep: 10 setMotion: MoveTo 368 191)
			)
			(2
				(car dispose:)
				(shadow dispose:)
				(dust init: setCycle: EndLoop self)
			)
			(3
				(dust dispose:)
				(self dispose:)
			)
		)
	)
)

(instance AlienDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (ego y?) 126)
					(ego setMotion: MoveTo (ego x?) 126 self)
				else
					(= cycles 2)
				)
			)
			(1
				(ego setMotion: MoveTo 228 126 self)
			)
			(2
				(ego
					view: 84
					setLoop: 0
					setCel: 255
					posn: 228 126
					ignoreControl:
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(3 (= seconds 2))
			(4
				(ego setLoop: 1 setCel: 255 setCycle: EndLoop self)
			)
			(5
				(ego
					setLoop: 2
					setCel: 0
					setCycle: Forward
					setMotion: MoveTo 218 133 self
				)
			)
			(6
				(ego setMotion: MoveTo 177 133 self)
			)
			(7
				(ego setMotion: MoveTo 173 151 self)
			)
			(8
				(ego setMotion: MoveTo 197 135 self)
			)
			(9
				(ego setMotion: MoveTo 175 150 self)
			)
			(10
				(ego setLoop: 3 setCel: 255 setCycle: EndLoop self)
			)
			(11
				(ego setLoop: 4 setCel: 255 setCycle: EndLoop self)
			)
			(12 (Print 43 19) (= dead TRUE))
		)
	)
)

(instance car of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 66
			setLoop: 0
			setCel: 0
			setPri: 15
			posn: 278 175
			xStep: 1
			ignoreActors:
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (Said 'look/car') (Print 43 20))
			)
		)
	)
)

(instance shadow of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 66
			setLoop: 0
			setCel: 1
			setPri: 14
			posn: 280 176
			xStep: 1
			ignoreActors:
		)
	)
)

(instance kid1 of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 66
			setLoop: 2
			setCel: 0
			posn: 139 109
			xStep: 6
			yStep: 3
			setCycle: Forward
			ignoreActors:
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (Said 'look/boy') (Print 43 21))
			)
		)
	)
)

(instance kid2 of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 66
			setLoop: 2
			setCel: 0
			posn: 139 109
			xStep: 6
			yStep: 3
			setCycle: Forward
			ignoreActors:
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (Said 'look/boy') (Print 43 21))
			)
		)
	)
)

(instance mom of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 66
			setLoop: 5
			setCel: 0
			posn: 144 109
			setCycle: Forward
			xStep: 5
			yStep: 3
			ignoreActors:
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (Said 'look/mom') (Print 43 22))
			)
		)
	)
)

(instance pop of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 66
			setLoop: 3
			setCel: 0
			posn: 142 111
			setCycle: Forward
			xStep: 5
			yStep: 3
			ignoreActors:
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (Said 'look/dad,man,alien') (Print 43 23))
			)
		)
	)
)

(instance fester of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 67
			setLoop: 0
			setCel: 0
			posn: 141 108
			xStep: 3
			yStep: 2
			setCycle: Forward
			ignoreActors:
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (Said 'look/man,blatz') (Print 43 24))
			)
		)
	)
)

(instance fballoon of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 67
			setLoop: 3
			setCel: 0
			setPri: 15
			posn: 217 103
			ignoreActors:
		)
	)
)

(instance pballoon of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 66
			setLoop: 6
			setCel: 0
			setPri: 15
			posn: 168 123
			stopUpd:
			ignoreActors:
		)
	)
)

(instance dust of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 66
			setLoop: 1
			setCel: 0
			setPri: 15
			posn: 287 166
			ignoreActors:
		)
	)
)

(instance w of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 70
			setLoop: 1
			setCel: 0
			setPri: 15
			posn: 95 34
			ignoreActors:
		)
	)
)

(instance orld of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 70
			setLoop: 0
			setCel: 0
			posn: 116 35
			setPri: 14
			stopUpd:
		)
	)
)

(instance o of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 70
			setLoop: 0
			setCel: 1
			posn: 155 36
			setPri: 14
			stopUpd:
		)
	)
)

(instance wonder of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 70
			setLoop: 0
			setCel: 2
			posn: 178 61
			setPri: 14
			stopUpd:
		)
	)
)

(instance door of View
	(properties)
)

(instance terminatorGrabs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Load VIEW 106)
				(ego setMotion: MoveTo 181 157 self)
			)
			(1
				(HandsOff)
				(= terminator (Actor new:))
				(terminator
					ignoreActors:
					illegalBits: 0
					posn: (ego x?) (ego y?)
				)
				(if wearingChickenHat
					(= wearingChickenHat FALSE)
					(PutInRoom iChickenHat -1)
					(= hat (Actor new:))
					(hat
						view: 755
						posn: (ego x?) (- (ego y?) 10)
						setAvoider: Avoider
						setStep: 5 5
						setCycle: Forward
						setMotion: MoveTo -10 170
						init:
					)
				)
				(ego
					view: 106
					setLoop: 4
					cel: 255
					ignoreHorizon:
					illegalBits: 0
					setCycle: EndLoop
					setPri: (ego priority?)
					setStep: 4 4
					setMotion: MoveTo (+ (ego x?) 11) (- (ego y?) 15) self
				)
				(RedrawCast)
			)
			(2
				(ego setLoop: 5 setCycle: Forward)
				(terminator
					view: 106
					setLoop: 6
					illegalBits: 0
					setPri: (ego priority?)
					setCycle: Forward
					init:
				)
				(= seconds 2)
			)
			(3
				(terminator
					view: 106
					setLoop: 7
					setPri: (ego priority?)
					setCycle: Forward
				)
				(= seconds 4)
			)
			(4
				(terminator setCel: 1)
				(RedrawCast)
				(curRoom drawPic: 430)
				(cast eachElementDo: #hide)
				(= termMouth (Prop new:))
				(termMouth
					view: 107
					setLoop: 0
					cel: 6
					posn: 61 189
					setPri: 14
					cycleSpeed: 0
					setScript: mouthActions
					init:
				)
				(= egoMouth (Prop new:))
				(egoMouth
					view: 107
					setLoop: 2
					posn: 149 86
					setPri: 14
					setCycle: Forward
					init:
				)
				(= eye (Prop new:))
				(eye
					view: 107
					setLoop: 1
					cel: 0
					posn: 160 52
					setPri: 14
					setScript: eyeActions
					init:
				)
				(= seconds 3)
			)
			(5
				(termMouth cycleSpeed: 1 setCycle: Forward)
				(Print 43 25 #font 600 #at -1 10 #width 300 #dispose)
				(= seconds 15)
			)
			(6
				(cls)
				(Print 43 26 #font 600 #at -1 10 #width 300 #dispose)
				(= seconds 15)
			)
			(7
				(cls)
				(Print 43 27 #font 600 #at -1 10 #width 300 #dispose)
				(= seconds 10)
			)
			(8
				(cls)
				(Print 43 28 #font 600 #at -1 10 #width 300 #dispose)
				(= seconds 15)
			)
			(9
				(cls)
				(cast eachElementDo: #show)
				(termMouth dispose:)
				(egoMouth dispose:)
				(eye dispose:)
				(door
					view: 121
					loop: 0
					cel: 0
					posn: 157 118
					init:
					stopUpd:
				)
				(orld init: addToPic:)
				(o init: addToPic:)
				(wonder init: addToPic:)
				(curRoom drawPic: 43)
				(= cycles 1)
			)
			(10
				(terminator setLoop: 7 setCycle: Forward)
				(= seconds 2)
			)
			(11 (= seconds 2))
			(12
				(terminator setLoop: 6)
				(= seconds 3)
			)
			(13
				(terminator hide:)
				(ego
					cycleSpeed: 2
					setCycle: BegLoop
					setMotion: MoveTo (- (ego x?) 11) (+ (ego y?) 15) self
				)
			)
			(14
				(ego
					view: 0
					illegalBits: -32768
					observeControl: 16384
					cycleSpeed: 0
					setCycle: Walk
					setLoop: -1
					setStep: 3 2
					setPri: -1
					loop: 2
				)
				(HandsOn)
				(= notifyCountdown 10)
				(curRoom setScript: tActions)
			)
		)
	)
)

(instance tActions of Script
	(method (doit)
		;this has been newly decompiled
		(if (!= curRoomNum newRoomNum) (return))
		(if (or (== local4 1) (== local4 7))
			(if (== (terminator cel?) 0)
			else
				(if (< (terminator loop?) 2) (== (terminator cel?) 4))
				(== (terminator cel?) 3)
			)
			(footPrint
				view: 106
				loop: (terminator loop?)
				cel: (terminator cel?)
				posn: (terminator x?) (terminator y?)
				ignoreActors: 1
				addToPic:
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load VIEW 106)
				(= seconds notifyCountdown)
			)
			(1
				(terminator
					view: 106
					setLoop: -1
					setCycle: Walk
					illegalBits: -32768
					observeControl: 16384
					show:
					setMotion: Chase ego 10 self
					setAvoider: Avoider
				)
				(= local4 1)
			)
			(2
				(HandsOff)
				(= local4 10)
				(terminator posn: (ego x?) (ego y?))
				(ego
					view: 106
					setLoop: 4
					cel: 255
					ignoreHorizon:
					illegalBits: 0
					setCycle: EndLoop
					setPri: (ego priority?)
					setStep: 4 4
					setMotion: MoveTo (+ (ego x?) 11) (- (ego y?) 15) self
				)
			)
			(3
				(ego setLoop: 5 setCycle: Forward)
				(terminator
					view: 106
					setLoop: 6
					illegalBits: 0
					setPri: (ego priority?)
					setCycle: Forward
					show:
				)
				(= seconds 4)
			)
			(4
				(terminator
					view: 106
					setLoop: 7
					setPri: (ego priority?)
					setCycle: Forward
				)
				(= seconds 5)
			)
			(5
				(ego hide:)
				((= newSound (Sound new:))
					number: 97
					loop: -1
					priority: 3
					play:
				)
				(terminator setLoop: 8 setCycle: Forward)
				(= seconds 6)
			)
			(6
				(newSound stop:)
				(terminator setLoop: 8 setCel: 0)
				(EgoDead 901 0 14 16)
			)
		)
	)
)

(instance eyeActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(eye cel: 1)
				(= cycles (Random 4 10))
			)
			(1
				(eye cel: 0)
				(= state -1)
				(= cycles (Random 10 60))
			)
		)
	)
)

(instance mouthActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(termMouth setCycle: Forward)
				(= seconds (Random 4 12))
			)
			(1
				(termMouth setCycle: 0 cel: 1)
				(= state -1)
				(= cycles (Random 7 15))
			)
		)
	)
)

(instance hatTrick of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 181 157 self)
			)
			(1
				(Print 43 29)
				(ego view: 0)
				(= wearingChickenHat FALSE)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)
