;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include sci.sh)
(use Main)
(use Intrface)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm65 0
)

(local
	[str 100]
	bubbles
	egoX
	local102
	local103
	local104
	local105
	local106
	local107
	local108
	local109
	local110
	local111
	local112
	sweptAway
)
(procedure (localproc_055c)
	(Print &rest #at -1 40)
)

(instance carp of Actor
	(properties)
)

(instance hand of View
	(properties)
)

(instance body of View
	(properties)
)

(instance divers of View
	(properties)
)

(instance weed1 of Extra
	(properties)
)

(instance weed2 of Extra
	(properties)
)

(instance weed3 of Extra
	(properties)
)

(instance weed4 of Extra
	(properties)
)

(instance weed5 of Extra
	(properties)
)

(instance weed6 of Extra
	(properties)
)

(instance weed7 of Extra
	(properties)
)

(instance can of View
	(properties)
)

(instance paper of View
	(properties)
)

(instance bottle of View
	(properties)
)

(instance metal of View
	(properties)
)

(instance findMusic of Sound
	(properties
		number 19
		priority 12
	)
)

(instance sweepMusic of Sound
	(properties
		number 37
		priority 12
	)
)

(instance rm65 of Room
	(properties
		picture 65
		style $0008
	)
	
	(method (init)
		(Load rsVIEW 12)
		(Load rsVIEW 91)
		(Load rsVIEW 161)
		(= local109 (if (Btst 7) 5 else 2))
		(weed1
			pauseCel: -1
			minPause: 8
			maxPause: 30
			minCycles: 10
			maxCycles: 20
			isExtra: 1
			view: 91
			loop: 5
			cel: 0
			posn: 304 173
			init:
			ignoreActors:
		)
		(weed2
			pauseCel: -1
			minPause: 8
			maxPause: 30
			minCycles: 10
			maxCycles: 20
			isExtra: 1
			view: 91
			loop: 5
			cel: 0
			posn: 253 94
			init:
			ignoreActors:
		)
		(weed3
			pauseCel: -1
			minPause: 10
			maxPause: 22
			minCycles: 10
			maxCycles: 20
			isExtra: 1
			view: 91
			loop: 5
			cel: 0
			posn: 282 109
			init:
			ignoreActors:
		)
		(weed4
			pauseCel: -1
			minPause: 12
			maxPause: 40
			minCycles: 10
			maxCycles: 20
			isExtra: 1
			view: 91
			loop: 6
			cel: 3
			posn: 3 149
			init:
			ignoreActors:
		)
		(weed5
			pauseCel: -1
			minPause: 14
			maxPause: 30
			minCycles: 6
			maxCycles: 18
			view: 91
			loop: 7
			cel: 0
			posn: 47 90
			init:
			ignoreActors:
			isExtra: 1
		)
		(weed6
			pauseCel: -1
			minPause: 14
			maxPause: 30
			minCycles: 6
			maxCycles: 20
			view: 91
			loop: 6
			cel: 0
			posn: 32 101
			init:
			ignoreActors:
			isExtra: 1
		)
		(weed7
			pauseCel: -1
			minPause: 12
			maxPause: 20
			minCycles: 12
			maxCycles: 20
			view: 91
			loop: 5
			cel: 0
			posn: 262 124
			init:
			ignoreActors:
			isExtra: 1
		)
		(can
			view: 91
			loop: 3
			cel: 9
			posn: 194 174
			init:
			ignoreActors:
			addToPic:
		)
		(paper
			view: 91
			loop: 3
			cel: 1
			posn: 106 163
			init:
			ignoreActors:
			addToPic:
		)
		(bottle
			view: 91
			loop: 3
			cel: 2
			posn: 45 116
			init:
			ignoreActors:
			addToPic:
		)
		(metal
			view: 91
			loop: 3
			cel: 3
			posn: 202 140
			init:
			ignoreActors:
			addToPic:
		)
		(hand
			view: 91
			loop: 3
			cel: (if (Btst 108) 11 else 10)
			posn: 296 129
			init:
			ignoreActors:
		)
		(body
			view: 91
			loop: 9
			cel: 0
			posn: 0 0
			init:
			setPri: 15
			ignoreActors:
		)
		(carp
			view: 165
			setCycle: 0
			posn: 0 169
			setPri: 13
			illegalBits: 0
			init:
			ignoreActors:
			setScript: carpScript
		)
		(super init:)
		(self setScript: rm65Script)
	)
	
	(method (dispose)
		(carpScript dispose:)
		(bodyScript dispose:)
		(airScript dispose:)
		(DisposeScript 988)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(switch (event type?)
			(evKEYBOARD
				(if (> local103 0) (= local103 3))
				(if
					(or
						(== (= temp0 (event message?)) KEY_F6)
						(== temp0 KEY_F8)
						(== temp0 KEY_F10)
					)
					(event claimed: 1)
				else
					(event claimed: 0)
				)
				(event claimed: removedBodyFromRiver)
			)
			(evSAID
				(cond 
					(
					(or (Said '<up') (Said '/rise,lid') (Said 'rise')) (rm65Script changeState: 3))
					((Said '/briefcase') (Print 65 0))
					(
						(Said
							'remove,(get<off)/belt,bcv,tank,fin,mask,suit,equipment'
						)
						(Print 65 1)
					)
					(
						(Said
							'[check]/air,pressure,(supply[<air]),(tank[<air]),(gauge[<air,pressure])'
						)
						(airScript changeState: 0)
					)
					((Said 'frisk/body') (Print 65 2))
					((Said 'look>')
						(cond 
							(
							(Said '[<at,around][/!*,clearwater,water,garbage,crud]') (localproc_055c 65 3))
							(
								(Said
									'/air,pressure,(supply[<air]),(tank[<air]),(gauge[<air,pressure])'
								)
								(airScript changeState: 0)
							)
							((Said '/hand')
								(cond 
									((and (ego inRect: 244 125 319 145) (Btst 108))
										(cSound stop:)
										(findMusic play:)
										(bodyScript changeState: 2)
									)
									((Btst 108) (localproc_055c 65 4))
									(else (localproc_055c 65 5))
								)
							)
							((Said '/rock,boulder')
								(if
									(and
										(not (Btst 108))
										(ego inRect: 244 125 319 145)
										(or (== (ego loop?) 0) (== (ego loop?) 3))
									)
									(localproc_055c 65 6)
								else
									(localproc_055c 65 7)
								)
							)
							((Said '/fish,carp,sucker') (localproc_055c 65 8))
							((Said '<up') (localproc_055c 65 9))
							((Said '/boat') (localproc_055c 65 10))
							((Said '/grass,plant,bush') (localproc_055c 65 11))
							((Said '/bottle[<beer]')
								(if (ego inRect: 0 90 87 130)
									(localproc_055c 65 12)
								else
									(localproc_055c 65 13)
								)
							)
							((Said '/newspaper,wrapper')
								(if (ego inRect: 0 130 110 189)
									(localproc_055c 65 14)
								else
									(localproc_055c 65 13)
								)
							)
							((Said '/metal')
								(if (ego inRect: 174 111 226 150)
									(localproc_055c 65 15)
								else
									(localproc_055c 65 13)
								)
							)
							((Said '/can[<coca,beer]')
								(if (ego inRect: 179 150 226 189)
									(localproc_055c 65 16)
								else
									(localproc_055c 65 13)
								)
							)
							((Said '/tire') (localproc_055c 65 17))
							((Said '/body,luis,body')
								(cond 
									((and (ego inRect: 244 125 319 145) (Btst 109)) (Print 65 18 #at -1 145))
									(
										(and
											(ego inRect: 244 125 319 145)
											(Btst 108)
											(not (Btst 109))
										)
										(bodyScript changeState: 2)
									)
									((Btst 108) (localproc_055c 65 4))
									(else (localproc_055c 65 5))
								)
							)
							(
								(or
									(Said '/bottom,muck,bed[<clearwater]')
									(Said '<down,ahead')
									(Said '/object,dirt')
								)
								(cond 
									(
										(and
											(not (Btst 108))
											(ego inRect: 244 125 319 145)
											(or (== (ego loop?) 0) (== (ego loop?) 3))
										)
										(localproc_055c 65 6)
									)
									((ego inRect: 179 150 226 189) (localproc_055c 65 16))
									((ego inRect: 0 130 110 189) (localproc_055c 65 14))
									((ego inRect: 174 111 226 150) (localproc_055c 65 15))
									((ego inRect: 0 90 87 130) (localproc_055c 65 12))
									((ego inRect: 87 115 180 130) (localproc_055c 65 10))
									(else (localproc_055c 65 19))
								)
							)
						)
					)
					((Said 'move,hoist,remove/rock')
						(if (ego inRect: 244 125 319 145)
							(if (not (Btst 108))
								(bodyScript changeState: 0)
							else
								(localproc_055c 65 20)
							)
						else
							(Print 65 21 #at -1 145 #draw)
						)
					)
					((Said 'get,remove,hoist/body,luis,body')
						(if (Btst 109)
							(bodyScript changeState: 3)
						else
							(Print 65 22 #at -1 145)
						)
					)
					((Said 'get,move,pull,hoist,remove>')
						(cond 
							((Said '/grass,plant,bush') (localproc_055c 65 23))
							((Said '/tire') (localproc_055c 65 24))
							((Said '/fish,carp,sucker') (localproc_055c 65 25))
							((Said '/boat') (localproc_055c 65 26))
							((Said '/bottle[<beer]')
								(if (ego inRect: 0 90 87 130)
									(localproc_055c 65 27)
								else
									(localproc_055c 65 28)
								)
							)
							((Said '/can[<coca,beer]')
								(if (ego inRect: 179 150 226 189)
									(localproc_055c 65 29)
								else
									(localproc_055c 65 28)
								)
							)
							((Said '/newspaper,wrapper')
								(if (ego inRect: 0 130 110 189)
									(localproc_055c 65 30)
								else
									(localproc_055c 65 28)
								)
							)
							((Said '/metal')
								(if (ego inRect: 174 111 226 150)
									(localproc_055c 65 31)
								else
									(localproc_055c 65 28)
								)
							)
							((Said '/hand,arm')
								(if (Btst 108)
									(if (ego inRect: 244 125 319 145)
										(Print 65 32 #at -1 145)
									else
										(Print 65 28 #at -1 145)
									)
								else
									(localproc_055c 65 33)
								)
							)
							(else (event claimed: 1) (localproc_055c 65 28))
						)
					)
				)
			)
		)
	)
)

(instance rm65Script of Script
	(properties)
	
	(method (doit)
		(cond 
			((> local103 1) (-- local103))
			((== local103 1) (= local103 0) (bodyScript changeState: 5))
			((> local104 1) (-- local104))
			((== local104 1) (= local104 0) (bodyScript changeState: 4))
			((> local111 1) (-- local111))
			((== local111 1) (= local111 0))
			((== local112 1) (carpScript changeState: 0))
			((== local112 0) (carpScript changeState: 1))
		)
		(if (not local105)
			(cond 
				((ego inRect: 226 85 319 125) (ego posn: (+ (ego x?) 4) (ego y?)))
				((< (ego x?) 100) (ego posn: (+ (ego x?) 1) (ego y?)))
				(else (ego posn: (+ (ego x?) 2) (ego y?)))
			)
		)
		(cond 
			((and sweptAway (> (ego x?) 320))
				(EgoDead
					{You are carried away and battered into unconsciousness on the rocks.}
				)
			)
			(
				(and
					(not sweptAway)
					(or
						(ego inRect: 265 75 319 102)
						(ego inRect: 295 103 319 115)
					)
				)
				(= sweptAway 1)
				(cSound stop:)
				(sweepMusic play:)
				(localproc_055c 65 34)
			)
		)
		(cond 
			((> local103 0) 0)
			((and (> scubaTankOxygen 0) (== local108 0))
				(= scubaTankOxygen (- scubaTankOxygen 4))
				(= local108 local109)
			)
			((> local108 0) (-- local108))
		)
		(cond 
			((and (== (ego edgeHit?) 4) local105) (DisposeScript 988) (cSound stop:) (curRoom newRoom: 62))
			((< (ego x?) 0) (curRoom newRoom: 63))
		)
		(cond 
			(
				(and
					(or
						(> scubaTankOxygen 2300)
						(and (<= 0 scubaTankOxygen) (<= scubaTankOxygen 10))
					)
					(not local105)
				)
				(if (Btst 7)
					(EgoDead
						{As you fight for air, blackness overcomes you. Next time, be more aware of your air tank's reserve.}
					)
				else
					(EgoDead
						{As you fight for air, blackness overcomes you. Next time, try using the bouyancy control vest to conserve air.}
					)
				)
			)
			(
				(and
					(<= 17 scubaTankOxygen)
					(<= scubaTankOxygen 27)
					(not local105)
				)
				(airScript changeState: 2)
				(= scubaTankOxygen 13)
				(localproc_055c 65 35)
			)
			(
				(and
					(<= 50 scubaTankOxygen)
					(<= scubaTankOxygen 60)
					(not local105)
				)
				(airScript changeState: 2)
				(= scubaTankOxygen 49)
				(localproc_055c 65 36)
			)
		)
		(cond 
			((> local107 1) (-- local107))
			((== local107 1) (= local107 0) (airScript changeState: 2))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 12
					setStep: 3 2
					posn: 1 (ego y?)
					setCycle: Walk
					setMotion: MoveTo 350 (ego y?)
					illegalBits: -32768
					init:
				)
				((= bubbles (Actor new:))
					view: 12
					setLoop: 4
					xStep: 4
					setCycle: Walk
					setPri: 14
					ignoreActors:
					illegalBits: 0
					init:
					stopUpd:
				)
				(self cue:)
			)
			(1
				(switch (ego loop?)
					(0 (= egoX (+ (ego x?) 21)))
					(1 (= egoX (- (ego x?) 21)))
					(2 (= egoX (ego x?)))
					(3 (= egoX (ego x?)))
				)
				(bubbles
					posn: egoX (ego y?)
					setPri: (ego priority?)
					setMotion: MoveTo (+ egoX 70) 15 self
				)
			)
			(2 (self changeState: 1))
			(3
				(HandsOff)
				(= local105 1)
				(ego
					view: 489
					setLoop: 2
					cycleSpeed: 2
					illegalBits: 0
					ignoreActors:
					setCycle: EndLoop self
				)
			)
			(4
				(= diverState 16)
				(ego
					setLoop: 4
					setStep: 5 3
					setPri: 15
					cycleSpeed: 0
					setCycle: Forward
					setMotion: MoveTo (- (ego x?) 80) 55 self
				)
			)
			(5
				(DisposeScript 988)
				(cSound stop:)
				(curRoom newRoom: 62)
			)
		)
	)
)

(instance airScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawCel 161 0 0 154 22 1)
				(= local107 15)
				(self cue:)
			)
			(1
				(Format @str 65 37 scubaTankOxygen)
				(Display @str dsCOORD 168 36 dsCOLOR 14 dsBACKGROUND 1)
			)
			(2
				(DrawCel 161 0 1 154 22 1)
				(Display @str dsCOORD 168 36 dsCOLOR 1 dsBACKGROUND 1)
			)
		)
	)
)

(instance carpScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(carp
					setLoop: 1
					setCel: 0
					setMotion: MoveTo 319 189
					setCycle: 0
				)
				(= local112 0)
				(= local111 150)
			)
			(1
				(carp
					setLoop: 1
					setCel: 1
					setMotion: MoveTo 0 169
					setCycle: 0
				)
				(= local112 1)
				(= local111 150)
			)
		)
	)
)

(instance bodyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 108)
				(hand setCel: 11)
				(localproc_055c 65 38 83)
				(self cue:)
			)
			(1
				(ego x: (- (ego x?) 4))
				(localproc_055c 65 39 83)
			)
			(2
				(body posn: 270 56)
				(Print 65 40 #at -1 145 #draw)
				(Bset 109)
			)
			(3
				(HandsOff)
				(User canInput: 1)
				(= removedBodyFromRiver 1)
				(SolvePuzzle 5)
				(airScript changeState: 2)
				(curRoom drawPic: 104 7)
				(cast eachElementDo: #dispose)
				(= local104 5)
				(= diverState 15)
			)
			(4
				(Display
					65
					41
					dsCOORD
					20
					100
					dsWIDTH
					200
					dsFONT
					0
					dsCOLOR
					15
				)
				(divers view: 12 posn: 200 95 setLoop: 7 init:)
				(= local103 1000)
			)
			(5
				(divers dispose:)
				(cSound stop:)
				(curRoom newRoom: 62)
			)
		)
	)
)
