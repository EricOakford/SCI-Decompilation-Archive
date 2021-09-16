;;; Sierra Script 1.0 - (do not remove this comment)
(script# LOLOTTE)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	regBFCastle 0
)

(local
	raven
	ravenPerch
	henchman
	ravenRoom
)
(instance henchCatchMusic of Sound
	(properties
		number 42
	)
)

(instance henchChaseMusic of Sound
	(properties
		number 41
	)
)

(instance regBFCastle of Region
	(method (init)
		(= keep TRUE)
		(Load VIEW 355)
		(Load VIEW 356)
		(Load VIEW 358)
		(= noWearCrown TRUE)
		(if (< gamePhase killedLolotte)
			(= ravenRoom 92)
		)
		(if (not howFast)
			(= ravenRoom 0)
		)
		(super init:)
	)
	
	(method (doit)
		(if
			(and
				(!= currentStatus egoFallDownStairs)
				lolotteAlive
				(== (ego script?) 0)
				(cast contains: henchman)
				(== (henchman script?) 0)
				(or
					(and
						(or (== curRoomNum 92) (== curRoomNum 91))
						(< (ego y?) 140)
						(< (ego distanceTo: henchman) 45)
						(> (Random 1 1000) 996)
					)
					(< (ego distanceTo: henchman) 20)
				)
			)
			(henchman setScript: henchChaseReg)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if (cast contains: henchman)
			(henchman setMotion: 0 setCycle: 0)
		)
		(if (== keep FALSE)
			(= noWearCrown FALSE)
			(super dispose:)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((Said 'blow/whistle')
							(if (ego has: iWhistle)
								(Print 604 0)
							else
								(DontHave)
							)
						)
						((Said 'look/wall')
							(Print 604 1)
						)
						(
							(and
								(cast contains: henchman)
								(Said 'converse[/goon,man]')
							)
							(if (and lolotteAlive (not henchChasingEgo))
								(if (< (ego distanceTo: henchman) 40)
									(henchman setScript: henchChaseReg)
								else
									(Print 604 2)
								)
							else
								(Print 604 3 #at -1 10)
							)
						)
						((Said '/goon,man>')
							(cond 
								((not (cast contains: henchman))
									(Print 604 4)
									(event claimed: TRUE)
								)
								((Said 'look')
									(if (and lolotteAlive (not henchChasingEgo))
										(Print 604 5)
									else
										(Print 604 6 #at -1 10)
									)
								)
								((Said 'awaken')
									(if (and lolotteAlive (not henchChasingEgo))
										(if (< (ego distanceTo: henchman) 40)
											(henchman setScript: henchChaseReg)
										else
											(Print 604 2)
										)
									else
										(Print 604 7)
									)
								)
								((Said 'get,capture')
									(Print 604 8)
								)
								((Said 'kiss')
									(Print 604 9)
								)
								((Said 'deliver')
									(Print 604 10)
									(event claimed: TRUE)
								)
							)
						)
						((Said '/raven,crow>')
							(cond 
								((not (cast contains: raven))
									(Print 604 11)
									(event claimed: TRUE)
								)
								((Said 'look')
									(Print 604 12)
								)
								((Said 'converse')
									(Print 604 13)
								)
								((Said 'get,capture')
									(Print 604 14)
								)
								((Said 'kiss')
									(Print 604 15)
								)
								((Said 'deliver')
									(Print 604 16)
									(event claimed: TRUE)
								)
							)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(henchChaseReg seconds: 0)
		(super newRoom: n)
	)
	
	(method (notify param1)
		(initializeRoom changeState: param1)
	)
)

(instance initializeRoom of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(henchChaseReg state: 0)
				(if
					(and
						(or (== curRoomNum 91) (== curRoomNum 92))
						howFast
					)
					((= ravenPerch (Prop new:))
						view: 358
						loop: 0
						cel: 0
						posn:
							(switch curRoomNum
								(91 258)
								(92 275)
							)
							(switch curRoomNum
								(91 136)
								(92 156)
							)
						init:
						stopUpd:
						setScript: ravenStuff
					)
				)
				(if
					(and
						(> gamePhase getPandoraBox)
						lolotteAlive
						(or
							(== curRoomNum 90)
							(== curRoomNum 91)
							(== curRoomNum 92)
							(== curRoomNum 93)
						)
					)
					((= henchman (Actor new:))
						view: 145
						loop: 4
						cel: (if (== curRoomNum 90) 1 else 0)
						posn:
							(switch curRoomNum
								(90 188)
								(91 210)
								(92 200)
								(93 124)
							)
							(switch curRoomNum
								(90 128)
								(91 119)
								(92 128)
								(93 129)
							)
						init:
						setAvoider: Avoider
						stopUpd:
					)
				)
			)
		)
	)
)

(instance ravenStuff of Script
	(method (doit)
		(if
			(and
				(== curRoomNum ravenRoom)
				(not (cast contains: raven))
			)
			(self changeState: 0)
		)
		(if
			(and
				(cast contains: raven)
				(!= (raven priority?) (ravenPerch priority?))
			)
			(raven setPri: (CoordPri (ravenPerch y?)))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						(not (cast contains: raven))
						(== curRoomNum ravenRoom)
						(or (== curRoomNum 91) (== curRoomNum 92))
					)
					((= raven (Actor new:))
						view: 356
						ignoreActors: TRUE
						illegalBits: 0
						xStep: 6
						yStep: 2
						posn:
							(switch curRoomNum
								(91 316)
								(92 51)
							)
							(switch curRoomNum
								(91 90)
								(92 81)
							)
						init:
					)
					(raven
						setPri: (+ (ravenPerch priority?) 1)
						setCycle: Forward
						setMotion: MoveTo (ravenPerch x?) (- (ravenPerch y?) 60) self
					)
				else
					(self changeState: 5)
				)
			)
			(1
				(raven
					setPri: (ravenPerch priority?)
					setMotion: MoveTo (ravenPerch x?) (- (ravenPerch y?) 40) self
				)
			)
			(2
				(raven
					view: 355
					loop: 3
					setMotion: 0
					setScript: preenShit
				)
				(= seconds 30)
			)
			(3
				(raven setScript: 0 setCycle: 0)
				(raven cel: 0 loop: 2 setCycle: EndLoop self)
			)
			(4
				(raven
					setScript: 0
					view: 356
					setPri: 11
					setLoop: -1
					setCycle: Forward
					setMotion:
						MoveTo
						(switch curRoomNum
							(91 316)
							(92 53)
						)
						(switch curRoomNum
							(91 103)
							(92 81)
						)
						self
				)
			)
			(5
				(if (cast contains: raven) (raven dispose:))
				(cond 
					((== ravenRoom 92) (= ravenRoom 91))
					(howFast (= ravenRoom 92))
				)
				(self cue:)
			)
			(6 (= state -1) (= seconds 30))
		)
	)
)

(instance preenShit of Script
	(method (init who)
		(super init: who)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (/ (Random 0 59) 10)
					(1
						(raven loop: 0 setCycle: EndLoop self)
					)
					(2
						(raven loop: 1 setCycle: EndLoop self)
					)
					(else
						(self cue:)
					)
				)
			)
			(1
				(raven loop: 3)
				(= seconds 4)
			)
			(2
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance henchChaseReg of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(henchman loop: 2 setCycle: EndLoop self)
			)
			(1
				(henchman loop: 0 setCycle: EndLoop self)
			)
			(2
				(= henchChasingEgo TRUE)
				(if (not (cast contains: henchman))
					(henchman
						setStep: 6 3
						posn:
							(switch curRoomNum
								(93 (- (ego x?) 30))
								(91 (+ (ego x?) 30))
							)
							(ego y?)
						init:
					)
				)
				(henchman
					view: 141
					ignoreActors: TRUE
					setStep: 6 3
					setCycle: Walk
					setMotion: Chase ego 15 self
				)
				(henchChaseMusic play:)
			)
			(3
				(User canControl: FALSE canInput: FALSE)
				(ego moveSpeed: 0 setMotion: 0)
				(henchChaseMusic dispose:)
				(henchCatchMusic play:)
				(= seconds 4)
			)
			(4
				(henchCatchMusic dispose:)
				(if (== curRoomNum newRoomNum)
					(= inCutscene TRUE)
					(curRoom newRoom: 81)
				else
					(User canControl: TRUE canInput: TRUE)
				)
			)
		)
	)
)
