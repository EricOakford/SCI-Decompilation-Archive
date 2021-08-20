;;; Sierra Script 1.0 - (do not remove this comment)
(script# 214)
(include game.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm214 0
)

(local
	local0
	usingStaff
	pts1 = [
		0 189
		0 0
		141 0
		141 159
		129 177
		16 177
		16 179
		41 179
		17 189
		]
	pts2 = [
		253 189
		248 185
		264 179
		296 179
		295 173
		200 177
		180 159
		180 0
		319 0
		319 189
		]
)
(instance rm214 of KQ5Room
	(properties
		picture 214
		south 213
	)
	
	(method (init)
		(super init:)
		(if (== ((inventory at: iStaff) owner?) 214)
			(brokenStaff init:)
		)
		(door init:)
		(switch prevRoomNum
			(213
				(if (and (not (Btst fSawBandits)) (Btst fBanditsEnterTemple))
					(horseBody1 init:)
					(horseHead1 init: setScript: (tailSwish new:))
					(horseTail1
						ignoreActors: TRUE
						init:
						setScript: (tailSwish new:)
					)
					(horseBody2 init:)
					(guardHead2 init: setScript: (tailSwish new:))
					(horseHead2 init: setScript: tailSwish)
					(horseTail2
						ignoreActors: TRUE
						init:
						setScript: (tailSwish new:)
					)
					(self setScript: guardEnter)
				else
					(ego
						view: 2
						posn: 158 186
						setStep: 2 1
						illegalBits: cWHITE
						observeControl: cYELLOW
						init:
					)
					(door setScript: musicScript)
				)
			)
			(18
				(HandsOff)
				(ego
					view: 2
					posn: 158 162
					setStep: 2 1
					loop: 11
					cel: 2
					observeControl: cYELLOW
					init:
				)
				(door cel: (- (NumCels door) 1))
				(self setScript: exitTemple)
			)
			(else 
				(ego
					view: 2
					posn: 158 162
					setStep: 2 1
					illegalBits: cWHITE
					observeControl: cYELLOW
					init:
				)
			)
		)
		(self setFeatures: horse1 horse2 sands room)
		(poly1 points: @pts1 size: 9)
		(poly2 points: @pts2 size: 10)
		(self addObstacle: poly1 poly2)
	)
	
	(method (doit &tmp edge)
		(cond 
			(script
				(script doit:)
			)
			((= edge (self edgeToRoom: (ego edgeHit?)))
				(curRoom newRoom: edge)
			)
		)
	)
	
	(method (dispose)
		(ego ignoreControl: cYELLOW)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(theMusic fade:)
		(theMusic2 fade:)
	)
)

(instance musicScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fBeenAtTemple))
					(Bset fBeenAtTemple)
					(theMusic number: 43 loop: 1 vol: 127 playBed: self)
				else
					(= cycles 1)
				)
			)
			(1
				(theMusic number: 44 loop: -1 vol: 127 play:)
			)
		)
	)
)

(instance exitTemple of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theAudio number: 8018 loop: 1 play:)
				(door setCycle: BegLoop self)
			)
			(1
				(SpeakAudio 7037)
				(door stopUpd:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance knockDoor of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 158 162 self)
			)
			(1
				((ego head?) hide:)
				(if usingStaff
					(ego
						normal: FALSE
						view: 362
						loop: 0
						cycleSpeed: 3
						cel: 0
						setCycle: EndLoop self
					)
				else
					(ego
						normal: FALSE
						view: 384
						loop: 0
						cel: 0
						setCycle: EndLoop self
					)
				)
			)
			(2
				(SpeakAudio 7038 self)
			)
			(3
				(if usingStaff
					(theAudio number: 8053 loop: 2 play:)
					(ego loop: 1 cycleSpeed: 6 setCycle: Forward)
					(= seconds 3)
				else
					(theAudio number: 8104 loop: -1 play:)
					(ego loop: 1 cycleSpeed: 8 setCycle: Forward)
					(= seconds 4)
				)
			)
			(4
				(if usingStaff
					(ego loop: 2 cel: 0 cycleSpeed: 3 setCycle: EndLoop self)
					(theAudio number: 8856 loop: 1 play:)
				else
					(theAudio stop:)
					(ego loop: 0)
					(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
				)
			)
			(5
				(cls)
				(if usingStaff
					(SpeakAudio 7039 self)
					(brokenStaff init:)
				else
					(SpeakAudio 782)
					(ego
						normal: TRUE
						view: 2
						loop: 7
						cel: 3
						cycleSpeed: 0
						setCycle: KQ5SyncWalk
					)
					((ego head?) show:)
					(HandsOn)
					(client setScript: 0)
				)
			)
			(6
				(if usingStaff
					(theAudio number: 8018 loop: 1 play:)
					(door setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(7 (= seconds 3))
			(8
				(theMusic3 stop:)
				(= cycles 10)
			)
			(9
				(cls)
				(curRoom newRoom: 18)
			)
		)
	)
)

(instance tailSwish of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: EndLoop)
				(= seconds (Random 2 4))
				(-- state)
			)
		)
	)
)

(instance guardEnter of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theMusic number: 44 loop: -1 vol: 127 play:)
				(horseBody1 cycleSpeed: 3 setCycle: EndLoop self)
			)
			(1
				(guard1 init: cycleSpeed: 3 setCycle: EndLoop self)
				(horseBody1 loop: 4 ignoreActors: TRUE)
			)
			(2
				(guard1
					view: 370
					setCycle: Walk
					setLoop: 0
					cycleSpeed: 0
					moveSpeed: 0
					setMotion: MoveTo 160 185 self
				)
			)
			(3
				(guard1 setLoop: 2 setMotion: MoveTo 163 161 self)
			)
			(4
				(guard1
					setLoop: 3
					cycleSpeed: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(5
				(SpeakAudio 9114 self)
			)
			(6
				(guard1 setLoop: 4 cycleSpeed: 4 setCycle: Forward)
				(theAudio number: 8053 loop: 3 play:)
				(= seconds 3)
			)
			(7
				(theAudio number: 8018 loop: 1 play:)
				(door setCycle: EndLoop self)
			)
			(8
				(cls)
				(guard1
					setLoop: 5
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(9 (= seconds 5))
			(10
				(guard1 view: 372 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(11
				(theAudio number: 8018 loop: 1 play:)
				(door setCycle: BegLoop)
				(guard1
					setLoop: 1
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 160 184 self
				)
			)
			(12
				(door stopUpd:)
				(guard1 setLoop: 3 setMotion: MoveTo 84 184 self)
			)
			(13
				(guard1 hide:)
				(horseBody1
					view: 368
					loop: 1
					cel: (- (NumCels guard1) 1)
					setCycle: BegLoop self
				)
			)
			(14
				(HandsOn)
				(curRoom newRoom: 213)
			)
		)
	)
)

(instance sands of RFeature
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (& (OnControl CMAP (event x?) (event y?)) cBLUE))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 764)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance horse1 of RFeature
	(properties
		nsTop 111
		nsBottom 175
		nsRight 37
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) userEvent))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 778)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 783)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance horse2 of RFeature
	(properties
		nsTop 111
		nsLeft 257
		nsBottom 177
		nsRight 319
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) userEvent))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 778)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 783)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 161
		y 163
		view 369
		priority 5
		signal (| ignrAct fixPriOn stopUpdOn)
		cycleSpeed 1
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) userEvent))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 779)
					(event claimed: TRUE)
				)
				(verbDo
					(HandsOff)
					(curRoom setScript: knockDoor)
					(event claimed: TRUE)
				)
				(verbTalk
					(SpeakAudio 7038)
					(event claimed: TRUE)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(iStaff
							(ego put: iStaff 214)
							(SolvePuzzle 2)
							(HandsOff)
							(= usingStaff TRUE)
							(curRoom setScript: knockDoor)
							(event claimed: TRUE)
						)
						(iWand
							(event claimed: FALSE)
						)
						(else 
							(SpeakAudio 785)
							(event claimed: TRUE)
						)
					)
				)
			)
		)
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) userEvent))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 780)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance guard1 of Actor
	(properties
		x 84
		y 184
		view 368
		loop 7
	)
)

(instance horseBody1 of Prop
	(properties
		x 66
		y 183
		view 368
		loop 1
		priority 12
		signal fixPriOn
		cycleSpeed 2
	)
)

(instance horseHead1 of Prop
	(properties
		x 64
		y 166
		view 368
		loop 2
		cycleSpeed 4
	)
)

(instance horseTail1 of Prop
	(properties
		x 93
		y 158
		view 368
		loop 3
		cycleSpeed 2
	)
)

(instance horseBody2 of Prop
	(properties
		x 223
		y 183
		view 378
		loop 2
		priority 12
		signal fixPriOn
		cycleSpeed 2
	)
)

(instance horseHead2 of Prop
	(properties
		x 214
		y 166
		view 378
		loop 3
		priority 14
		signal fixPriOn
		cycleSpeed 4
	)
)

(instance horseTail2 of Prop
	(properties
		x 243
		y 158
		view 378
		loop 4
		cycleSpeed 2
	)
)

(instance guardHead2 of Prop
	(properties
		x 222
		y 146
		view 378
		loop 5
		priority 15
		signal fixPriOn
		cycleSpeed 1
	)
)

(instance brokenStaff of Prop
	(properties
		x 169
		y 162
		view 362
		loop 3
		priority 5
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init: &rest)
		(self stopUpd:)
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) userEvent))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 781)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 784)
					(event claimed: TRUE)
				)
			)
		)
	)
)

(instance poly1 of Polygon
	(properties
		type PBarredAccess
	)
)

(instance poly2 of Polygon
	(properties
		type PBarredAccess
	)
)
