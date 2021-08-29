;;; Sierra Script 1.0 - (do not remove this comment)
(script# 91)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm91 0
)

(local
	brigandsBehindLog
	local1
	[local2 108]
	theArrowID
	local111
	local112 =  40
	local113 =  30
	theNewView
	gEgoObjLoop
	theNewView_2
	[newView 28]
	local145
	local146
	local147
	local148 =  1
	local149
	egoKilled
)
(procedure (localproc_000c param1 param2 param3 &tmp temp0)
	(= local146 0)
	(= local145 1)
	(if (< (= temp0 (Abs (- param1 param2))) 2)
		(= local145 (if (== temp0 param3) 0 else 2))
	)
	(if
	(and (< param1 2) (< param2 2) (!= param1 param3))
		(= local146 (if (== param2 0) 6 else -6))
	)
)

(procedure (localproc_0064 param1 param2 param3 param4 &tmp [temp0 2])
	(if (localproc_0086 param1 param2 param3 param4)
	else
		(localproc_01ab param1 param2 param3 param4)
	)
)

(procedure (localproc_0086 param1 param2 param3 param4 &tmp temp0 temp1)
	(= temp0
		(localproc_02a3
			param1
			param2
			(ego x?)
			(- (ego y?) 35)
			(ego x?)
			(- (ego y?) 15)
		)
	)
	(if
		(==
			temp0
			(= temp1
				(localproc_02a3
					param3
					param4
					(ego x?)
					(- (ego y?) 35)
					(ego x?)
					(- (ego y?) 15)
				)
			)
		)
		(return 0)
	)
	(= temp0
		(localproc_02a3
			(ego x?)
			(- (ego y?) 35)
			param1
			param2
			param3
			param4
		)
	)
	(return
		(if
			(==
				temp0
				(= temp1
					(localproc_02a3
						(ego x?)
						(- (ego y?) 15)
						param1
						param2
						param3
						param4
					)
				)
			)
			(return 0)
		else
			(= theNewView
				(-
					(/
						(+
							(* param4 (ego x?))
							(- (* param4 param1))
							(- (* param2 (ego x?)))
							(* param2 param3)
						)
						(- param3 param1)
					)
					(ego y?)
				)
			)
			(= gEgoObjLoop (ego loop?))
			(if (< (ego x?) param1)
				(= theNewView_2 1)
			else
				(= theNewView_2 0)
			)
			(return 1)
		)
	)
)

(procedure (localproc_01ab param1 param2 param3 param4 &tmp temp0 temp1)
	(= temp0
		(localproc_02a3
			param1
			param2
			(- (ego y?) 25)
			(- (ego x?) 8)
			(- (ego y?) 25)
			(+ (ego x?) 8)
		)
	)
	(if
		(==
			temp0
			(= temp1
				(localproc_02a3
					param3
					param4
					(- (ego y?) 25)
					(- (ego x?) 8)
					(- (ego y?) 25)
					(+ (ego x?) 8)
				)
			)
		)
		(return 0)
	)
	(= temp0
		(localproc_02a3
			(- (ego y?) 25)
			(- (ego x?) 8)
			param1
			param2
			param3
			param4
		)
	)
	(return
		(if
			(==
				temp0
				(= temp1
					(localproc_02a3
						(- (ego y?) 25)
						(+ (ego x?) 8)
						param1
						param2
						param3
						param4
					)
				)
			)
			(return 0)
		else
			(= theNewView (- -15 (Random 0 20)))
			(= gEgoObjLoop (ego loop?))
			(return 1)
		)
	)
)

(procedure (localproc_02a3 param1 param2 param3 param4 param5 param6)
	(return
		(if
			(>
				(+
					(* param6 param3)
					(- (* param5 param4))
					(* param1 (- param4 param6))
					(* param2 (- param5 param3))
				)
				0
			)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (localproc_02d5 param1 param2 param3 &tmp temp0 temp1 temp2 temp3)
	(if (< param1 (ego x?))
		(= temp1 (+ (- (ego x?) param1) (Random 0 35)))
	else
		(= temp1 (- (- (ego x?) param1) (Random 0 35)))
	)
	(= temp2 (- (ego y?) param2))
	(= temp3 (/ (* temp1 2) 128))
	(= temp1 (/ temp1 8))
	(= temp2 (/ temp2 8))
	(= [local2 param3] param1)
	(= [local2 (+ param3 9)] param2)
	(= temp0 1)
	(while (<= temp0 4)
		(= [local2 (+ param3 temp0)]
			(+ [local2 (+ param3 temp0 -1)] temp1)
		)
		(= [local2 (+ param3 temp0 9)]
			(+ [local2 (+ param3 temp0 8)] (- temp2 temp3))
		)
		(++ temp0)
	)
	(= temp0 5)
	(while (<= temp0 8)
		(= [local2 (+ param3 temp0)]
			(+ [local2 (+ param3 temp0 -1)] temp1)
		)
		(= [local2 (+ param3 temp0 9)]
			(+ [local2 (+ param3 temp0 8)] temp2 temp3)
		)
		(++ temp0)
	)
)

(instance rm91 of Room
	(properties
		picture 91
		style DISSOLVE
		horizon 100
		north 93
		east 92
	)
	
	(method (init)
		(LoadMany VIEW vArchers vArrows vEgoJumping)
		(Load SOUND (SoundFX 73))
		(super init:)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(NormalEgo)
		(cSound stop:)
		(brigandS number: (SoundFX 73) init: play:)
		(archer1 setPri: 5 init: stopUpd:)
		(archer2 setPri: 7 init: stopUpd:)
		(archer3 setPri: 15 init: stopUpd:)
		(archer4 setPri: 3 init: stopUpd:)
		(archer5 setPri: 3 init: stopUpd:)
		(archer6 setPri: 3 init: stopUpd:)
		(spearman4 setPri: 13 init: stopUpd:)
		(spearman5 setPri: 13 init: stopUpd:)
		(self setScript: egoEnters)
		(switch prevRoomNum
			(93
				(Bclr fBrigsBehindLog)
				(ego
					posn: 143 108
					illegalBits: (| cWHITE cLBLUE)
					setPri: 6
					init:
					setMotion: MoveTo 143 114 egoEnters
				)
			)
			(465
				(ego
					posn: 143 115
					illegalBits: (| cWHITE cLBLUE)
					setPri: 6
					init:
					setMotion: MoveTo 143 102 egoEnters
				)
			)
			(else 
				(Bset fBrigsBehindLog)
				(= brigandsBehindLog TRUE)
				(spearman1 setPri: 3 init: stopUpd:)
				(spearman2 setPri: 3 init: stopUpd:)
				(spearman3 setPri: 3 init: stopUpd:)
				(ego
					posn: 309 158
					illegalBits: (| cWHITE cBROWN)
					setPri: 11
					init:
					setMotion: MoveTo 286 158 egoEnters
				)
			)
		)
	)
	
	(method (doit &tmp egoLoop egoPriority egoX egoY)
		(= egoLoop (ego loop?))
		(= egoPriority (ego priority?))
		(= egoX (ego x?))
		(= egoY (ego y?))
		(= local147 0)
		(while (<= local147 24)
			(if (!= [newView local147] 0)
				(localproc_000c
					[newView (+ local147 1)]
					egoLoop
					[newView (+ local147 2)]
				)
				(if (< [newView local147] -29)
					(= egoKilled 1)
					(= [newView local147] -36)
					(if (== local145 0) (= local145 3))
					(if (== local145 2) (= local145 4))
				)
				([newView (+ local147 3)]
					setCel: local145
					setPri: (- egoPriority 1)
					posn: (+ egoX local146) (+ egoY [newView local147])
				)
			)
			(= local147 (+ local147 4))
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn91)
		(brigandS stop:)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [str 30])
		(switch (event type?)
			(mouseDown
				(cond 
					((super handleEvent: event))
					((MouseClaimed ego event shiftDown)
						(HighPrint (Format @str 91 0 @userName)
							;%s, you're in big trouble!
						)
					)
				)
			)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							((Said '[<at,around][/place,area]')
								(HighPrint 91 1)
								;The cliffs are rocky and riddled with niches that could conceal things.
								;Brigand archers for instance.  There is a pile of logs off to the north.
							)
							((Said '[<at]/boulder')
								(HighPrint 91 2)
								;You seem to see movement, but you are not certain what is over there.
							)
							((Said '[<at]/cliff')
								(HighPrint 91 3)
								;The rock walls look steep.
							)
							((Said '[<at]/log')
								(HighPrint 91 4)
								;It is difficult to see them because of the cliff walls, but you think you can make out something or things behind the logs.
							)
							((Said '[<at]/bandit,archer,man')
								(HighPrint 91 5)
								;From what you can see, you realize that you are in a very dangerous situation.
							)
							((or (Said '<up') (Said '/sky'))
								(HighPrint 91 6)
								;You see archers perched on the cliffs.
							)
							((or (Said '<down') (Said '/ground,grass'))
								(HighPrint 91 7)
								;You'd better keep your head up.
							)
							((Said '/south,west')
								(HighPrint 91 8)
								;You see sheer cliff walls.
							)
							((Said '/north')
								(HighPrint 91 9)
								;You see a suspicious pile of logs blocking the passage in this canyon.
							)
							((Said '/east')
								(HighPrint 91 10)
								;You see where the canyon turns north.
							)
						)
					)
					((Said 'climb,hop,hop')
						(cond 
							(
								(and
									(< 100 (ego x?))
									(< (ego x?) 160)
									(& (ego onControl: origin) cLBLUE)
								)
								(= local149 1)
								(ego setScript: climbOverLogs)
							)
							(
								(and
									(> (ego y?) 117)
									(< 100 (ego x?))
									(< (ego x?) 160)
									(& (ego onControl: origin) cBROWN)
								)
								(= local149 0)
								(ego setScript: climbOverLogs)
							)
							(else
								(HighPrint 91 11)
								;The only way to climb is over the logs and you're not in a good place for that.
							)
						)
					)
				)
			)
		)
	)
)

(instance archer1 of Prop
	(properties
		y 87
		x 35
		view vArchers
		priority 5
	)
	
	(method (doit)
		(cond 
			(
				(and
					(not local1)
					(& (ego onControl: origin) cLRED)
					(< local111 local112)
				)
				(= local1 1)
				(archer1 setScript: shoot1 0 0)
				(archer2 setScript: shoot2 0 1)
				(archer3 setScript: shoot3 0 2)
				(archer4 setScript: shoot4 0 3)
				(archer5 setScript: shoot5 0 4)
				(archer6 setScript: shoot6 0 5)
			)
			(
				(or
					(& (ego onControl: origin) cBLACK)
					(& (ego onControl: origin) cBROWN)
					(> local111 local112)
				)
				(= local1 0)
				(if (and local148 (> local111 local112))
					(HighPrint 91 12)
					;Looks like the archers are running short on arrows.
					(= local148 0)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if
			(or
				(MouseClaimed archer1 event shiftDown)
				(MouseClaimed archer2 event shiftDown)
				(MouseClaimed archer4 event shiftDown)
				(MouseClaimed archer5 event shiftDown)
				(MouseClaimed archer6 event shiftDown)
			)
			(HighPrint 91 13)
			;A brigand archer!
		)
	)
)

(instance archer2 of Prop
	(properties
		y 112
		x 27
		view vArchers
		priority 7
	)
)

(instance archer3 of Prop
	(properties
		y 185
		x 64
		view vArchers
		loop 1
		priority 5
	)
	
	(method (handleEvent event)
		(if (MouseClaimed archer3 event shiftDown)
			(HighPrint 91 14)
			;One bad dude!
		)
	)
)

(instance archer4 of Actor
	(properties
		y 77
		x 296
		view vArchers
		loop 2
		priority 3
		illegalBits $0000
	)
)

(instance archer5 of Actor
	(properties
		y 75
		x 216
		view vArchers
		loop 4
		priority 3
		illegalBits $0000
	)
)

(instance archer6 of Actor
	(properties
		y 80
		x 247
		view vArchers
		loop 4
		priority 3
		illegalBits $0000
	)
)

(instance spearman1 of View
	(properties
		y 123
		x 94
		view vArchers
		loop 5
		priority 3
	)
	
	(method (handleEvent event)
		(if
			(or
				(MouseClaimed spearman1 event shiftDown)
				(MouseClaimed spearman2 event shiftDown)
				(MouseClaimed spearman3 event shiftDown)
				(MouseClaimed spearman4 event shiftDown)
				(MouseClaimed spearman5 event shiftDown)
			)
			(HighPrint 91 15)
			;A brigand ground fighter!
		)
	)
)

(instance spearman2 of View
	(properties
		y 119
		x 119
		view vArchers
		loop 5
		cel 1
		priority 3
	)
)

(instance spearman3 of View
	(properties
		y 117
		x 146
		view vArchers
		loop 5
		cel 2
		priority 3
	)
)

(instance spearman4 of Actor
	(properties
		y 201
		x 136
		view vArchers
		loop 6
		priority 13
		illegalBits $0000
	)
)

(instance spearman5 of Actor
	(properties
		y 211
		x 254
		view vArchers
		loop 6
		cel 1
		priority 13
	)
)

(class Shooter of Script
	(properties
		arrowPt 0
		arrowID 0
		arrowLoop 0
		addArrowX 0
		addArrowY 0
		archRestCel 0
	)
	
	(method (changeState newState &tmp egoX)
		(switch (= state newState)
			(0
				(if (not local1) (self dispose:) (return))
				(= egoX (ego x?))
				(= arrowPt (* register 18))
				(switch register
					(2
						(= addArrowX 11)
						(= addArrowY -55)
						(= archRestCel 4)
						(= arrowLoop 1)
					)
					(3
						(= addArrowX -7)
						(= addArrowY 0)
						(= archRestCel 3)
						(if (< 255 egoX)
							(client setLoop: 4)
							(= arrowLoop 3)
						else
							(client setLoop: 2)
							(= arrowLoop 2)
						)
					)
					(4
						(= addArrowX 0)
						(= addArrowY 4)
						(= archRestCel 3)
						(cond 
							((< 194 egoX) (client setLoop: 3) (= arrowLoop 3))
							((< 183 egoX) (client setLoop: 4) (= arrowLoop 3))
							(else (client setLoop: 2) (= arrowLoop 2))
						)
					)
					(5
						(= addArrowX -3)
						(= addArrowY 1)
						(= archRestCel 3)
						(if (< 215 egoX)
							(client setLoop: 2)
							(= arrowLoop 2)
						else
							(client setLoop: 4)
							(= arrowLoop 3)
						)
					)
					(else 
						(= addArrowX 9)
						(= addArrowY -26)
						(= archRestCel 2)
						(= arrowLoop 0)
					)
				)
				(= cycles (Random 5 15))
			)
			(1
				(localproc_02d5
					(+ (client x?) addArrowX)
					(+ (client y?) addArrowY)
					arrowPt
				)
				(client setCycle: CycleTo (- (NumCels client) 2) 1 self)
			)
			(2
				((= arrowID (Prop new:))
					view: vArrows
					setLoop: arrowLoop
					setCel: 0
					setPri: 11
					ignoreActors:
					posn: [local2 (+ arrowPt 1)] [local2 (+ arrowPt 10)]
					init:
				)
				(client setCel: (+ (client cel?) 1))
				(= cycles 1)
			)
			(3
				(arrowID
					setCel: 1
					posn: [local2 (+ arrowPt 2)] [local2 (+ arrowPt 11)]
				)
				(= cycles 1)
			)
			(4
				(arrowID
					setCel: 2
					posn: [local2 (+ arrowPt 3)] [local2 (+ arrowPt 12)]
				)
				(= cycles 1)
			)
			(5
				(client setCycle: CycleTo archRestCel 1)
				(arrowID
					posn: [local2 (+ arrowPt 4)] [local2 (+ arrowPt 13)]
				)
				(= cycles 1)
			)
			(6
				(arrowID
					setCel: 3
					posn: [local2 (+ arrowPt 5)] [local2 (+ arrowPt 14)]
				)
				(= cycles 1)
			)
			(7
				(if
					(and
						(== (ego script?) 0)
						(localproc_0064
							[local2 (+ arrowPt 5)]
							[local2 (+ arrowPt 14)]
							[local2 (+ arrowPt 6)]
							[local2 (+ arrowPt 15)]
						)
					)
					(= theArrowID arrowID)
					(ego setScript: egoHit)
					(self changeState: 0)
				else
					(arrowID
						posn: [local2 (+ arrowPt 6)] [local2 (+ arrowPt 15)]
					)
					(= cycles 1)
				)
			)
			(8
				(if
					(and
						(== (ego script?) 0)
						(localproc_0064
							[local2 (+ arrowPt 6)]
							[local2 (+ arrowPt 15)]
							[local2 (+ arrowPt 7)]
							[local2 (+ arrowPt 16)]
						)
					)
					(= theArrowID arrowID)
					(ego setScript: egoHit)
					(self changeState: 0)
				else
					(arrowID
						setCel: 4
						posn: [local2 (+ arrowPt 7)] [local2 (+ arrowPt 16)]
					)
					(= cycles 1)
				)
			)
			(9
				(if
					(and
						(== (ego script?) 0)
						(localproc_0064
							[local2 (+ arrowPt 7)]
							[local2 (+ arrowPt 16)]
							[local2 (+ arrowPt 8)]
							[local2 (+ arrowPt 17)]
						)
					)
					(= theArrowID arrowID)
					(ego setScript: egoHit)
					(self changeState: 0)
				else
					(arrowID
						posn: [local2 (+ arrowPt 8)] [local2 (+ arrowPt 17)]
					)
					(= cycles 1)
				)
			)
			(10
				(if (< (++ local111) local113)
					(arrowID addToPic:)
				else
					(arrowID dispose:)
				)
				(self changeState: 0)
			)
		)
	)
)

(instance shoot1 of Shooter)

(instance shoot2 of Shooter)

(instance shoot3 of Shooter)

(instance shoot4 of Shooter)

(instance shoot5 of Shooter)

(instance shoot6 of Shooter)

(instance egoHit of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theArrowID dispose:)
				(cond 
					((ego has: iChainmail)
						(TakeDamage 10)
					)
					((ego has: iLeather)
						(TakeDamage 20)
					)
					(else
						(TakeDamage 35)
					)
				)
				(= temp0 0)
				(while (<= temp0 24)
					(if (not [newView temp0])
						(= [newView temp0] theNewView)
						(= [newView (+ temp0 1)] gEgoObjLoop)
						(= [newView (+ temp0 2)] theNewView_2)
						((= [newView (+ temp0 3)] (View new:))
							view: vArrows
							setLoop: 5
							setCel: 1
							ignoreActors:
							init:
						)
						(break)
					)
					(= temp0 (+ temp0 4))
				)
				(if (or (<= [egoStats HEALTH] 0) egoKilled)
					(= cycles 3)
				else
					(self dispose:)
				)
			)
			(1
				(if egoKilled
					(HighPrint 91 16)
					;You have a splintering headache that's doing you in.
				)
				(EgoDead 91 17
					#icon vEgoDefeatedMagic 0 9
					#title {You knew this job was dangerous....}
					;This was not the ending you had in mind when you read the manual on "How to be a Hero".
					;Either you just aren't ready to take on this situation, or there is a better way to accomplish your mission.
				)
			)
		)
	)
)

(instance egoEnters of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
			)
			(1
				(if (not (Btst fBeenIn91))
					(TimePrint 4 91 18)
					;You have a bad feeling about this place.
				)
				(= seconds 4)
			)
			(2
				(archer4
					setLoop: 2
					setCel: 0
					ignoreHorizon:
					setMotion: MoveTo (archer4 x?) (- (archer4 y?) 25)
				)
				(archer5
					setLoop: 3
					setCel: 0
					ignoreHorizon:
					setMotion: MoveTo (archer5 x?) (- (archer5 y?) 25)
				)
				(archer6
					setLoop: 4
					setCel: 0
					ignoreHorizon:
					setMotion: MoveTo (archer6 x?) (- (archer6 y?) 25) self
				)
			)
			(3
				(archer4 stopUpd:)
				(archer5 stopUpd:)
				(archer6 stopUpd:)
				(spearman4
					setLoop: 6
					setCel: 0
					ignoreActors:
					illegalBits: 0
					ignoreHorizon:
					setMotion: MoveTo (spearman4 x?) (- (spearman4 y?) 25) self
				)
			)
			(4
				(spearman5
					setLoop: 6
					setCel: 1
					ignoreActors:
					illegalBits: 0
					ignoreHorizon:
					setMotion: MoveTo (spearman5 x?) (- (spearman5 y?) 25) self
				)
			)
			(5
				(if (not (Btst fBeenIn91))
					(TimePrint 4 91 19)
					;You have an even worse feeling about this place.
				)
				(= seconds 4)
			)
			(6
				(spearman4 stopUpd:)
				(spearman5 stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance climbOverLogs of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if local149
					(ego
						view: vEgoJumping
						setLoop: loopE
						setCel: 0
						illegalBits: 0
						ignoreActors:
						setMotion: MoveTo (ego x?) (- (ego y?) 4) self
					)
				else
					(ego
						view: vEgoJumping
						setLoop: loopW
						setCel: 0
						illegalBits: 0
						ignoreActors:
						setMotion: MoveTo (ego x?) (- (ego y?) 4) self
					)
				)
			)
			(1
				(ego
					setCel: 1
					setMotion: MoveTo (ego x?) (- (ego y?) 4) self
				)
			)
			(2
				(ego
					setCel: 2
					setMotion: MoveTo (ego x?) (- (ego y?) 3) self
				)
			)
			(3
				(if local149
					(ego
						setCel: 3
						setMotion: MoveTo (ego x?) (- (ego y?) 5) self
					)
				else
					(ego
						setCel: 3
						setPri: 7
						setMotion: MoveTo (ego x?) (- (ego y?) 4) self
					)
				)
			)
			(4
				(if local149
					(ego
						setCel: 4
						setPri: 6
						setMotion: MoveTo (ego x?) (- (ego y?) 4) self
					)
				else
					(ego
						setCel: 4
						setMotion: MoveTo (ego x?) (- (ego y?) 4) self
					)
				)
			)
			(5
				(if local149
					(ego
						setCel: 5
						setMotion: MoveTo (ego x?) (+ (ego y?) 5) self
					)
				else
					(ego
						setCel: 5
						setMotion: MoveTo (ego x?) (+ (ego y?) 8) self
					)
				)
			)
			(6
				(if local149
					(ego
						setCel: 6
						setMotion: MoveTo (ego x?) (+ (ego y?) 5) self
					)
				else
					(ego
						setCel: 6
						setMotion: MoveTo (ego x?) (+ (ego y?) 8) self
					)
				)
			)
			(7
				(if local149
					(ego
						view: vEgo
						setLoop: loopN
						setCel: 0
						setMotion: MoveTo (ego x?) (+ (ego y?) 5) self
					)
				else
					(ego
						view: vEgo
						setLoop: loopS
						setCel: 0
						setMotion: MoveTo (ego x?) (+ (ego y?) 8) self
					)
				)
			)
			(8
				(NormalEgo)
				(if local149
					(ego loop: loopN setPri: 6 illegalBits: (| cWHITE cLBLUE))
				else
					(ego loop: loopS setPri: 7 illegalBits: (| cWHITE cBROWN))
				)
				(if (and local149 brigandsBehindLog)
					(= numBrigands 3)
					(curRoom newRoom: vBrigand)
				else
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)

(instance brigandS of Sound
	(properties
		number 73
		priority 3
		loop -1
	)
)
