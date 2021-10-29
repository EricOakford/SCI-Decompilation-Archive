;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
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
	rm63 0
)

(local
	[str 100]
	egoX
	local101
	moorePosn
	local103
	local104
	local105
	local106
	local107
	local108
	local109
	local110
)
(procedure (localproc_03fc)
	(Print &rest #at -1 40)
)

(instance bubble of Actor
	(properties)
)

(instance mooreBubble of Actor
	(properties)
)

(instance badge of View
	(properties)
)

(instance carp of Actor
	(properties)
)

(instance weed0 of Extra
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

(instance frogLegs of Prop
	(properties)
)

(instance moore of View
	(properties)
)

(instance rm63 of Room
	(properties
		picture 63
		style $0008
	)
	
	(method (init)
		(User canInput: 1)
		(User canControl: 1)
		(Load rsSCRIPT 988)
		(Load rsVIEW 12)
		(Load rsVIEW 165)
		(Load rsVIEW 91)
		(Load rsVIEW 161)
		(mooreScript changeState: 0)
		(weed0
			pauseCel: -1
			minPause: 10
			maxPause: 30
			minCycles: 8
			maxCycles: 18
			isExtra: 1
			view: 91
			posn: 7 163
			loop: 4
			cel: 0
			setPri: 12
			init:
		)
		(weed1
			pauseCel: -1
			minPause: 10
			maxPause: 30
			minCycles: 8
			maxCycles: 18
			isExtra: 1
			view: 91
			loop: 6
			cel: 2
			posn: 14 171
			setPri: 13
			init:
		)
		(weed2
			pauseCel: -1
			minPause: 10
			maxPause: 30
			minCycles: 8
			maxCycles: 18
			isExtra: 1
			view: 91
			loop: 6
			cel: 2
			posn: 102 95
			setPri: 6
			init:
		)
		(weed3
			pauseCel: -1
			minPause: 10
			maxPause: 20
			minCycles: 10
			maxCycles: 18
			isExtra: 1
			view: 91
			loop: 6
			cel: 2
			posn: 267 118
			setPri: 8
			init:
		)
		(weed4
			pauseCel: -1
			minPause: 10
			maxPause: 30
			minCycles: 8
			maxCycles: 18
			isExtra: 1
			view: 91
			loop: 7
			cel: 2
			posn: 37 123
			setPri: 8
			init:
		)
		((View new:)
			view: 91
			loop: 3
			cel: 5
			posn: 70 179
			setPri: 12
			init:
			addToPic:
			ignoreActors:
		)
		((View new:)
			view: 91
			loop: 3
			cel: 6
			posn: 39 108
			setPri: 7
			init:
			addToPic:
		)
		((View new:)
			view: 91
			loop: 3
			cel: 4
			posn: 278 154
			setPri: 11
			init:
			addToPic:
			ignoreActors:
		)
		((View new:)
			view: 91
			loop: 3
			cel: 0
			posn: 143 167
			setPri: 12
			init:
			addToPic:
			ignoreActors:
		)
		((View new:)
			view: 91
			loop: 3
			cel: 3
			posn: 240 122
			setPri: 8
			init:
			addToPic:
			ignoreActors:
		)
		(if (InRoom 18)
			(badge
				view: 91
				loop: 3
				cel: 7
				posn: 197 153
				init:
				ignoreActors:
			)
		)
		(if (not (< howFast 30))
			(carp
				view: 165
				setCycle: 0
				posn: -25 154
				setPri: 12
				illegalBits: 0
				init:
				ignoreActors:
				setScript: carpScript
			)
		)
		(= local108 (if (Btst 7) 6 else 2))
		(= moorePosn (Random 1 7))
		(moore
			view: 12
			loop: 5
			posn:
				(switch moorePosn
					(1 158)
					(2 150)
					(3 286)
					(4 275)
					(5 236)
					(6 95)
					(7 198)
				)
				(switch moorePosn
					(1 93)
					(2 186)
					(3 95)
					(4 121)
					(5 145)
					(6 124)
					(7 162)
				)
			init:
			addToPic:
		)
		(frogLegs
			view: 12
			loop: 6
			posn: (moore x?) (moore y?)
			setCycle: Forward
			cycleSpeed: 2
			init:
		)
		(bubble
			view: 12
			setLoop: 4
			xStep: 4
			setCycle: Walk
			setPri: 12
			ignoreActors:
			illegalBits: 0
			init:
			stopUpd:
			setScript: bubScript
		)
		(mooreBubble
			view: 12
			setLoop: 4
			xStep: 4
			setCycle: Forward
			setPri: 12
			ignoreActors:
			illegalBits: 0
			init:
			stopUpd:
			setScript: mooreScript
		)
		(ego init:)
		(super init:)
		(self setScript: rm63Script)
	)
	
	(method (dispose)
		(mooreScript dispose:)
		(carpScript dispose:)
		(airScript dispose:)
		(bubScript dispose:)
		(DisposeScript 988)
		(super dispose:)
	)
)

(instance rm63Script of Script
	(properties)
	
	(method (doit)
		(if (and (== (self state?) 2) (not local109))
			(ego x: (+ (ego x?) 1))
			(if (> local103 1) (-- local103))
			(if (== local103 1)
				(= local103 0)
				(bubScript changeState: 1)
			)
		)
		(if (> local104 1) (-- local104))
		(if (== local104 1)
			(= local104 0)
			(mooreScript changeState: 1)
		)
		(if (> local105 1) (-- local105))
		(if (== local105 1)
			(= local105 0)
			(if (== local110 1)
				(carpScript changeState: 0)
			else
				(carpScript changeState: 1)
			)
		)
		(if (and (> scubaTankOxygen 0) (>= local107 0))
			(= scubaTankOxygen (- scubaTankOxygen 2))
			(= local107 local108)
		)
		(if (> local107 0) (-- local107))
		(cond 
			(
				(and
					(or
						(and (<= 0 scubaTankOxygen) (<= scubaTankOxygen 6))
						(> scubaTankOxygen 2300)
					)
					(not local109)
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
					(<= 8 scubaTankOxygen)
					(<= scubaTankOxygen 15)
					(not local109)
				)
				(airScript changeState: 2)
				(= scubaTankOxygen 7)
				(localproc_03fc 63 0)
			)
			(
				(and
					(<= 40 scubaTankOxygen)
					(<= scubaTankOxygen 50)
					(not local109)
				)
				(airScript changeState: 2)
				(= scubaTankOxygen 39)
				(localproc_03fc 63 1)
			)
		)
		(if (> local106 1) (-- local106))
		(if (== local106 1)
			(= local106 0)
			(airScript changeState: 2)
		)
		(cond 
			((== (ego edgeHit?) 4)
				(curRoom
					newRoom: (if local109
						(cSound stop:)
						(DisposeScript 988)
						62
					else
						64
					)
				)
			)
			((== (ego edgeHit?) 2) (curRoom newRoom: 65))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== prevRoomNum 60)
					(= local109 1)
					(HandsOff)
					(ego
						view: 489
						setStep: 2 2
						setLoop: 0
						posn: 30 50
						setPri: 15
						illegalBits: 0
						ignoreActors: 1
						setMotion: MoveTo 110 130 self
					)
					(= diverState 16)
				else
					(ego
						posn:
						(switch prevRoomNum
							(64 30)
							(65 290)
						) (ego y?)
						setMotion: MoveTo (if (== prevRoomNum 65) -10 else 350) (ego y?)
					)
					(self changeState: 2)
				)
			)
			(1
				(ego
					setLoop: 1
					setPri: 15
					setMotion: MoveTo 400 134
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(= local109 0)
				(ego
					cycleSpeed: 0
					view: 12
					setCycle: Walk
					setStep: 3 2
					setLoop: -1
					setPri: -1
					illegalBits: -32768
					ignoreActors: 0
				)
				(if (== prevRoomNum 60)
					(if (not (Btst 6))
						(localproc_03fc 63 2)
						(= local109 1)
						(ego
							setStep: 2 2
							illegalBits: 0
							ignoreActors:
							setMotion: MoveTo 225 132 self
						)
					else
						(ego setMotion: MoveTo 400 134)
						(HandsOn)
					)
				)
			)
			(3
				(localproc_03fc 63 3)
				(self cue:)
			)
			(4
				(User canControl: 0 canInput: 0)
				(= local109 1)
				(ego
					view: 489
					setLoop: 2
					cycleSpeed: 2
					illegalBits: 0
					ignoreActors:
					setCycle: EndLoop self
				)
			)
			(5
				(ego
					setLoop: 4
					setStep: 5 3
					setPri: 15
					cycleSpeed: 0
					setCycle: Forward
					setMotion: MoveTo (- (ego x?) 90) 55 self
				)
			)
			(6
				(DisposeScript 988)
				(cSound stop:)
				(curRoom newRoom: 62)
			)
		)
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
					((Said 'chat') (Print 63 4))
					((Said '/briefcase') (Print 63 5))
					(
						(Said
							'remove,(get<off)/belt,bcv,tank,fin,mask,suit,equipment'
						)
						(Print 63 6)
					)
					(
						(Said
							'[check]/air,pressure,(supply[<air]),(tank[<air]),(gauge[<air,pressure])'
						)
						(airScript changeState: 0)
					)
					(
					(or (Said '<up') (Said '/rise,lid') (Said 'rise')) (rm63Script changeState: 4))
					((Said 'look>')
						(cond 
							(
								(Said
									'/air,pressure,(supply[<air]),(tank[<air]),(gauge[<air,pressure])'
								)
								(airScript changeState: 0)
							)
							(
							(Said '[<at,around][/!*,clearwater,water,garbage,crud]') (localproc_03fc 63 7))
							((Said '<up') (localproc_03fc 63 8))
							(
								(or
									(Said '/bottom,muck,bed[<clearwater]')
									(Said '<down,ahead')
									(Said '/object,dirt')
								)
								(cond 
									((ego inRect: 0 147 110 191) (localproc_03fc 63 9))
									((ego inRect: 239 129 319 191) (localproc_03fc 63 10))
									((ego inRect: 0 95 118 112) (localproc_03fc 63 11))
									(
										(and
											(InRoom 18)
											(or
												(and (ego inRect: 146 141 186 158) (== (ego loop?) 0))
												(and (ego inRect: 213 143 245 158) (== (ego loop?) 1))
												(and (ego inRect: 184 137 205 149) (== (ego loop?) 2))
												(and (ego inRect: 184 155 206 169) (== (ego loop?) 3))
											)
										)
										(localproc_03fc 63 12)
										(localproc_03fc 63 13)
									)
									((ego inRect: 108 139 189 189) (localproc_03fc 63 14))
									((ego inRect: 200 100 319 129) (localproc_03fc 63 15))
									((ego inRect: 105 109 159 139) (localproc_03fc 63 16))
									((ego inRect: 0 112 108 139) (localproc_03fc 63 17))
									(else (localproc_03fc 63 18))
								)
							)
							((Said '/auto')
								(if (< (ego x?) 160)
									(localproc_03fc 63 11)
								else
									(localproc_03fc 63 19)
								)
							)
							((Said '/number,license')
								(if (ego inRect: 0 95 118 112)
									(localproc_03fc 63 20)
								else
									(localproc_03fc 63 19)
								)
							)
							((Said '/newspaper')
								(if (ego inRect: 239 129 319 191)
									(localproc_03fc 63 10)
								else
									(localproc_03fc 63 19)
								)
							)
							((Said '/billfold')
								(if (ego inRect: 0 147 110 191)
									(localproc_03fc 63 9)
								else
									(localproc_03fc 63 19)
								)
							)
							((Said '/can[<beer,coca]')
								(if (ego inRect: 108 139 189 189)
									(localproc_03fc 63 14)
								else
									(localproc_03fc 63 19)
								)
							)
							((Said '/metal,scrap')
								(if (ego inRect: 200 100 319 129)
									(localproc_03fc 63 21)
								else
									(localproc_03fc 63 19)
								)
							)
							((Said '/gold,metal,flicker,badge')
								(cond 
									((ego has: 18) (event claimed: 0))
									(
										(or
											(and (ego inRect: 146 141 186 158) (== (ego loop?) 0))
											(and (ego inRect: 213 143 245 158) (== (ego loop?) 1))
											(and (ego inRect: 184 137 205 149) (== (ego loop?) 2))
											(and (ego inRect: 184 155 206 169) (== (ego loop?) 3))
										)
										(localproc_03fc 63 12)
										(localproc_03fc 63 13)
									)
									(else (localproc_03fc 63 19))
								)
							)
							((Said '/glasses,glass')
								(if (ego inRect: 105 109 159 139)
									(localproc_03fc 63 16)
								else
									(localproc_03fc 63 19)
								)
							)
							((Said '/tire,drum[<oil]')
								(if (ego inRect: 0 112 108 139)
									(localproc_03fc 63 17)
								else
									(localproc_03fc 63 19)
								)
							)
							((Said '/cave') (localproc_03fc 63 22))
							((Said '/rock,boulder') (localproc_03fc 63 23))
							((Said '/plant,bush,grass') (localproc_03fc 63 24))
							(
								(or
									(Said '/fish,carp,sucker')
									(Said '/school[<fish,carp,sucker]')
								)
								(localproc_03fc 63 25)
							)
							((Said '/crawfish') (localproc_03fc 63 26))
							((Said '/diver') (localproc_03fc 63 27))
						)
					)
					((Said 'check,frisk/auto') (localproc_03fc 63 28))
					((Said '[get]/crap') (localproc_03fc 63 29))
					((Said '[get]/leak') (localproc_03fc 63 30))
					(
					(or (Said 'remove,recover,tow/auto') (Said 'call/tow')) (localproc_03fc 63 31))
					((Said 'move/rock') (localproc_03fc 63 32))
					((Said 'get,move,pull,hoist,remove>')
						(cond 
							((Said '/crawfish') (localproc_03fc 63 33))
							((Said '/muck,garbage') (localproc_03fc 63 34))
							((Said '/grass,plant') (localproc_03fc 63 35))
							((Said '/fish,carp,sucker') (localproc_03fc 63 36))
							((Said '/auto') (localproc_03fc 63 31))
							((Said '/newspaper')
								(if (ego inRect: 239 129 319 191)
									(localproc_03fc 63 37)
								else
									(NotClose)
								)
							)
							((Said '/billfold')
								(if (ego inRect: 0 147 110 191)
									(localproc_03fc 63 38)
								else
									(NotClose)
								)
							)
							((Said '/can[<beer,coca]')
								(if (ego inRect: 108 139 189 189)
									(localproc_03fc 63 39)
								else
									(NotClose)
								)
							)
							((Said '/glasses')
								(if (ego inRect: 105 109 159 139)
									(localproc_03fc 63 39)
								else
									(NotClose)
								)
							)
							((Said '/tire,drum[<oil]')
								(if (ego inRect: 0 112 108 139)
									(localproc_03fc 63 39)
								else
									(NotClose)
								)
							)
							((Said '/badge,gold,flicker')
								(cond 
									((not (InRoom 18)) (AlreadyTook))
									(
										(or
											(and (ego inRect: 146 141 186 158) (== (ego loop?) 0))
											(and (ego inRect: 213 143 245 158) (== (ego loop?) 1))
											(and (ego inRect: 184 137 205 149) (== (ego loop?) 2))
											(and (ego inRect: 184 155 206 169) (== (ego loop?) 3))
										)
										(badge posn: 0 0)
										(ego get: 18)
										(SolvePuzzle 2)
										(localproc_03fc 63 40 83)
										(localproc_03fc 63 41 83)
									)
									(else (NotClose))
								)
							)
							((Said '/scrap')
								(if (ego inRect: 200 100 319 129)
									(localproc_03fc 63 39)
								else
									(NotClose)
								)
							)
							((Said '/object,metal')
								(cond 
									(
										(or
											(and (ego inRect: 146 141 186 158) (== (ego loop?) 0))
											(and (ego inRect: 213 143 245 158) (== (ego loop?) 1))
											(and (ego inRect: 184 137 205 149) (== (ego loop?) 2))
											(and (ego inRect: 184 155 206 169) (== (ego loop?) 3))
										)
										(if (InRoom 18)
											(badge posn: 0 0)
											(ego get: 18)
											(SolvePuzzle 2)
											(localproc_03fc 63 40 83)
											(localproc_03fc 63 41 83)
										else
											(AlreadyTook)
										)
									)
									((ego inRect: 239 129 319 191) (localproc_03fc 63 37))
									((ego inRect: 0 147 110 191) (localproc_03fc 63 38))
									((ego inRect: 108 139 189 189) (localproc_03fc 63 39))
									((ego inRect: 200 100 319 129) (localproc_03fc 63 39))
									((ego inRect: 105 109 159 139) (localproc_03fc 63 39))
									((ego inRect: 0 112 108 139) (localproc_03fc 63 39))
									(else (localproc_03fc 63 42 83))
								)
							)
						)
					)
				)
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
					setLoop: 0
					setCel: 0
					setMotion: MoveTo 365 148
					setCycle: 0
				)
				(= local110 0)
				(= local105 150)
			)
			(1
				(carp
					setLoop: 0
					setCel: 1
					setMotion: MoveTo -25 154
					setCycle: 0
				)
				(= local110 1)
				(= local105 150)
			)
		)
	)
)

(instance mooreScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local104 (Random 4 8))
				(if (== prevRoomNum 60)
					(cSound number: 17 loop: -1 play:)
				)
			)
			(1
				(= local101 (- (frogLegs x?) 20))
				(mooreBubble
					posn: local101 (moore y?)
					startUpd:
					setPri: (frogLegs priority?)
					setMotion: MoveTo (+ local101 50) 15 self
				)
			)
			(2
				(mooreBubble stopUpd:)
				(= local104 (Random 20 26))
			)
		)
	)
)

(instance airScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DrawCel 161 0 0 227 16 1)
				(= local106 15)
				(self cue:)
			)
			(1
				(Format @str 63 43 scubaTankOxygen)
				(Display @str dsCOORD 244 30 dsCOLOR 14 dsBACKGROUND 1)
			)
			(2
				(DrawCel 161 0 1 227 16 1)
				(Display @str dsCOORD 244 30 dsCOLOR 1 dsBACKGROUND 1)
			)
		)
	)
)

(instance bubScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local103 (Random 18 26)))
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
					setMotion: MoveTo (+ egoX 50) 15 self
				)
			)
			(2
				(bubble stopUpd:)
				(= local103 (Random 22 32))
			)
		)
	)
)
