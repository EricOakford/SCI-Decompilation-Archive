;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include sci.sh)
(use Main)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm64 0
)

(local
	[str 100]
	egoX
	local101
	local102
	local103
	local104
	local105
	local106
	local107
	local108
	local109
)
(procedure (localproc_000c)
	(Print &rest #at -1 40)
)

(instance bubble of Actor
	(properties)
)

(instance carp of Actor
	(properties)
)

(instance knife of View
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

(instance theWaves of Prop
	(properties)
)

(instance waterFall of Prop
	(properties)
)

(instance bubbles of Prop
	(properties)
)

(instance rm64 of Room
	(properties
		picture 64
		style $0008
	)
	
	(method (init)
		(User canInput: 1)
		(Load rsVIEW 12)
		(Load rsVIEW 91)
		(Load rsVIEW 161)
		(= local106 (if (Btst 7) 6 else 2))
		(theWaves
			view: 91
			loop: 1
			cel: 0
			posn: 58 17
			setPri: 15
			setCycle: Forward
			cycleSpeed: 1
			init:
		)
		(waterFall
			view: 91
			loop: 0
			cel: 1
			posn: 27 23
			setPri: 15
			init:
			setCycle: Forward
		)
		(bubbles
			view: 91
			loop: 8
			cel: 0
			posn: 5 35
			setPri: 15
			init:
			setCycle: Forward
		)
		(if (< howFast 30)
			(theWaves stopUpd:)
			(waterFall stopUpd:)
			(bubbles stopUpd:)
		)
		(weed1
			pauseCel: -1
			minPause: 10
			maxPause: 40
			minCycles: 6
			maxCycles: 12
			isExtra: 1
			view: 91
			loop: 7
			cel: 3
			posn: 284 94
			init:
			ignoreActors:
		)
		(weed2
			pauseCel: -1
			minPause: 10
			maxPause: 40
			minCycles: 6
			maxCycles: 12
			isExtra: 1
			view: 91
			loop: 4
			cel: 3
			posn: 297 181
			init:
		)
		(weed3
			pauseCel: -1
			minPause: 10
			maxPause: 30
			minCycles: 6
			maxCycles: 12
			isExtra: 1
			view: 91
			loop: 6
			cel: 2
			posn: 12 135 12
			init:
		)
		(weed4
			pauseCel: -1
			minPause: 10
			maxPause: 30
			minCycles: 6
			maxCycles: 12
			isExtra: 1
			view: 91
			loop: 7
			cel: 0
			posn: 262 95
			init:
		)
		((View new:)
			view: 91
			loop: 3
			cel: 0
			posn: 168 124
			setPri: 8
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 91
			loop: 3
			cel: 1
			posn: 138 108
			setPri: 7
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 91
			loop: 3
			cel: 4
			posn: 256 111
			setPri: 7
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 91
			loop: 3
			cel: 3
			posn: 76 130
			setPri: 8
			init:
			ignoreActors:
			addToPic:
		)
		(if (InRoom 14)
			(knife
				view: 91
				loop: 3
				cel: 8
				posn: 142 146
				setPri: 10
				init:
				ignoreActors:
			)
		)
		(carp
			view: 165
			setCycle: 0
			posn: 355 175
			setPri: 13
			illegalBits: 0
			init:
			ignoreActors:
			setScript: carpScript
		)
		(super init:)
		(self setScript: rm64Script)
	)
	
	(method (dispose)
		(carpScript dispose:)
		(airScript dispose:)
		(DisposeScript 988)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(switch (event type?)
			(evKEYBOARD
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
			)
			(evSAID
				(cond 
					((Said '/briefcase') (Print 64 0))
					(
						(Said
							'remove,(get<off)/belt,bcv,tank,fin,mask,suit,equipment'
						)
						(Print 64 1)
					)
					(
						(Said
							'[check]/air,pressure,(supply[<air]),(tank[<air]),(gauge[<air,pressure])'
						)
						(airScript changeState: 0)
					)
					((Said 'swim/waterfall') (Print 64 2))
					(
					(or (Said '<up') (Said '/rise,lid') (Said 'rise')) (rm64Script changeState: 3))
					((Said 'look>')
						(cond 
							((Said '/waterfall') (localproc_000c 64 3))
							(
								(Said
									'/air,pressure,(supply[<air]),(tank[<air]),(gauge[<air,pressure])'
								)
								(airScript changeState: 0)
							)
							(
							(Said '[<at,around][/!*,clearwater,water,garbage,crud]') (localproc_000c 64 4))
							((Said '<up') (localproc_000c 64 5))
							(
								(or
									(Said '/bottom,muck,bed[<clearwater]')
									(Said '<down,ahead')
									(Said '/object,dirt')
								)
								(cond 
									((ego inRect: 106 94 165 124) (localproc_000c 64 6))
									((ego inRect: 240 94 284 122) (localproc_000c 64 6))
									(
										(and
											(InRoom 14)
											(or
												(and (ego inRect: 92 128 150 152) (== (ego loop?) 0))
												(and (ego inRect: 150 128 184 152) (== (ego loop?) 1))
												(and (ego inRect: 120 133 160 139) (== (ego loop?) 2))
												(and (ego inRect: 120 135 160 165) (== (ego loop?) 3))
											)
										)
										(localproc_000c 64 7)
									)
									((ego inRect: 155 105 193 141) (localproc_000c 64 8))
									((ego inRect: 50 107 106 132) (localproc_000c 64 9))
									((ego inRect: 193 134 240 164) (localproc_000c 64 10))
									(else (localproc_000c 64 11))
								)
							)
							((Said '/newspaper')
								(if
									(or
										(ego inRect: 106 94 165 124)
										(ego inRect: 240 94 284 122)
									)
									(localproc_000c 64 12)
								else
									(localproc_000c 64 13)
								)
							)
							((Said '/can[<beer,coca]')
								(if (ego inRect: 155 105 193 141)
									(localproc_000c 64 8)
								else
									(localproc_000c 64 13)
								)
							)
							((Said '/metal,scrap')
								(if (ego inRect: 50 107 106 132)
									(localproc_000c 64 9)
								else
									(localproc_000c 64 13)
								)
							)
							((Said '/light,metal,glint,knife')
								(if (InRoom 14)
									(if
										(or
											(and (ego inRect: 92 128 150 152) (== (ego loop?) 0))
											(and (ego inRect: 150 128 184 152) (== (ego loop?) 1))
											(and (ego inRect: 120 133 160 139) (== (ego loop?) 2))
											(and (ego inRect: 120 135 160 165) (== (ego loop?) 3))
										)
										(localproc_000c 64 14)
									else
										(localproc_000c 64 13)
									)
								else
									(event claimed: 0)
								)
							)
							((Said '/bottle,glass')
								(if (ego inRect: 193 134 240 164)
									(localproc_000c 64 10)
								else
									(localproc_000c 64 13)
								)
							)
							((Said '/cave') (localproc_000c 64 15))
							((Said '/rock,boulder') (localproc_000c 64 16))
							((Said '/plant,bush,grass') (localproc_000c 64 17))
							((Said '<behind/rock') (localproc_000c 64 18))
							(
								(or
									(Said '/fish,carp,sucker')
									(Said '/school[<fish,carp,sucker]')
								)
								(localproc_000c 64 19)
							)
						)
					)
					((Said 'move/rock') (localproc_000c 64 20))
					((Said '[get]/crap') (localproc_000c 64 21))
					((Said '[get]/leak') (localproc_000c 64 22))
					((Said 'get,move,pull,hoist,remove>')
						(cond 
							((Said '/muck,garbage') (localproc_000c 64 23))
							((Said '/grass,plant') (localproc_000c 64 24))
							((Said '/fish,carp,sucker') (localproc_000c 64 25))
							((Said '/newspaper')
								(if
									(or
										(ego inRect: 106 94 165 124)
										(ego inRect: 240 94 284 122)
									)
									(localproc_000c 64 12)
								else
									(localproc_000c 64 26)
								)
							)
							((Said '/can[<beer,coca]')
								(if (ego inRect: 155 105 193 141)
									(localproc_000c 64 27)
								else
									(localproc_000c 64 26)
								)
							)
							((Said '/metal,scrap')
								(if (ego inRect: 50 107 106 132)
									(localproc_000c 64 28)
								else
									(localproc_000c 64 26)
								)
							)
							((Said '/bottle')
								(if (ego inRect: 193 134 240 164)
									(localproc_000c 64 29)
								else
									(localproc_000c 64 26)
								)
							)
							((Said '/knife')
								(if (InRoom 14)
									(if
										(or
											(and (ego inRect: 92 128 150 152) (== (ego loop?) 0))
											(and (ego inRect: 150 128 184 152) (== (ego loop?) 1))
											(and (ego inRect: 120 133 160 139) (== (ego loop?) 2))
											(and (ego inRect: 120 135 160 165) (== (ego loop?) 3))
										)
										(knife posn: 0 0)
										(ego get: 14)
										(SolvePuzzle 2)
										(localproc_000c 64 30 83)
										(localproc_000c 64 31 83)
										(localproc_000c 64 32 83)
									else
										(Print 64 33)
									)
								else
									(AlreadyTook)
								)
							)
							((Said '/object')
								(cond 
									(
										(or
											(ego inRect: 106 94 165 124)
											(ego inRect: 240 94 284 122)
										)
										(localproc_000c 64 12)
									)
									((ego inRect: 155 105 193 141) (localproc_000c 64 27))
									((ego inRect: 50 107 106 132) (localproc_000c 64 28))
									((ego inRect: 193 134 240 164) (localproc_000c 64 29))
									(
										(and
											(InRoom 14)
											(or
												(and (ego inRect: 92 128 150 152) (== (ego loop?) 0))
												(and (ego inRect: 150 128 184 152) (== (ego loop?) 1))
												(and (ego inRect: 120 133 160 139) (== (ego loop?) 2))
												(and (ego inRect: 120 135 160 165) (== (ego loop?) 3))
											)
										)
										(knife posn: 0 0)
										(ego get: 14)
										(SolvePuzzle 2)
										(localproc_000c 64 30 83)
										(localproc_000c 64 31 83)
										(localproc_000c 64 32 83)
									)
									(else (localproc_000c 64 26 83))
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance rm64Script of Script
	(properties)
	
	(method (doit)
		(if (> local108 1) (-- local108))
		(if (== local108 1)
			(= local108 0)
			(if (== local109 1)
				(carpScript changeState: 0)
			else
				(carpScript changeState: 1)
			)
		)
		(if (not local103)
			(ego x: (+ 1 (ego x?)))
			(if (> local102 1) (-- local102))
			(if (== local102 1)
				(= local102 0)
				(rm64Script changeState: 1)
			)
			(cond 
				((== (ego edgeHit?) 2) (curRoom newRoom: 63))
				(
					(and
						(or
							(and (< (ego x?) 83) (< (ego y?) 92))
							(and (> (ego x?) 285) (< (ego y?) 92))
						)
						(== local107 0)
					)
					(localproc_000c 64 34)
					(= local107 1)
				)
				((and (> (ego x?) 105) (< (ego x?) 265)) (= local107 0))
			)
		)
		(if (and (> scubaTankOxygen 0) (>= local105 0))
			(= scubaTankOxygen (- scubaTankOxygen 2))
			(= local105 local106)
		)
		(if (> local105 0) (-- local105))
		(cond 
			(
				(and
					(or
						(and (<= 0 scubaTankOxygen) (<= scubaTankOxygen 6))
						(> scubaTankOxygen 2300)
					)
					(not local103)
				)
				(localproc_000c 64 35)
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
					(<= 8 scubaTankOxygen)
					(<= scubaTankOxygen 15)
					(not local103)
				)
				(airScript changeState: 2)
				(= scubaTankOxygen 7)
				(localproc_000c 64 36)
			)
			(
				(and
					(<= 40 scubaTankOxygen)
					(<= scubaTankOxygen 50)
					(not local103)
				)
				(airScript changeState: 2)
				(= scubaTankOxygen 39)
				(localproc_000c 64 37)
			)
		)
		(if (> local104 1) (-- local104))
		(if (== local104 1)
			(= local104 0)
			(airScript changeState: 2)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 12
					setStep: 3 2
					posn: 290 (ego y?)
					setCycle: Walk
					setMotion: MoveTo -10 (ego y?)
					illegalBits: -32768
					init:
				)
				(bubble
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
				(= local102 (Random 18 26))
			)
			(1
				(switch (ego loop?)
					(0 (= egoX (+ (ego x?) 21)))
					(1 (= egoX (- (ego x?) 21)))
					(2 (= egoX (ego x?)))
					(3 (= egoX (ego x?)))
				)
				(bubble
					posn: egoX (ego y?)
					startUpd:
					setPri: (ego priority?)
					setMotion: MoveTo (+ egoX 30) 15 self
				)
			)
			(2
				(bubble stopUpd:)
				(= local102 (Random 24 36))
			)
			(3
				(User canControl: 0 canInput: 0)
				(= local103 1)
				(ego
					view: 489
					setLoop: 3
					cycleSpeed: 2
					illegalBits: 0
					ignoreActors:
					setCycle: EndLoop self
				)
			)
			(4
				(= diverState 16)
				(ego
					setLoop: 5
					setStep: 5 3
					setPri: 15
					cycleSpeed: 0
					setCycle: Forward
					setMotion: MoveTo (+ (ego x?) 90) 55 self
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
				(DrawCel 161 4 0 40 16 1)
				(= local104 15)
				(self cue:)
			)
			(1
				(Format @str 64 38 scubaTankOxygen)
				(Display @str dsCOORD 54 30 dsCOLOR 14 dsBACKGROUND 1)
			)
			(2
				(DrawCel 161 4 1 40 16 1)
				(Display @str dsCOORD 54 30 dsCOLOR 1 dsBACKGROUND 1)
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
					setCel: 1
					setMotion: MoveTo 65 191
					setCycle: 0
				)
				(= local109 0)
				(= local108 100)
			)
			(1
				(carp
					setLoop: 0
					setCel: 0
					setMotion: MoveTo 355 175
					setCycle: 0
				)
				(= local109 1)
				(= local108 150)
			)
		)
	)
)
