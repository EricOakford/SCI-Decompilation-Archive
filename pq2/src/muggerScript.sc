;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include sci.sh)
(use Main)
(use Intrface)
(use Wander)
(use Avoider)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	muggerScript 0
)

(local
	keithIsHere
	muggerGone
	mugger
	local3
	toldMuggerToHalt
	calledKeith
	muggerScared
	local7
	local8
	local9
	newProp
	local11
	local12
	egoX
	egoY
	local15
	readMuggerRights
	local17
)
(procedure (localproc_0036)
	(Print &rest #at -1 15)
)

(procedure (localproc_0045 param1 param2)
	(DirLoop
		param1
		(GetAngle
			(param1 x?)
			(param1 y?)
			(param2 x?)
			(param2 y?)
		)
	)
	(if (== argc 3)
		(DirLoop
			param2
			(GetAngle
				(param2 x?)
				(param2 y?)
				(param1 x?)
				(param1 y?)
			)
		)
	)
)

(instance mugMusic of Sound
	(properties
		number 26
	)
)

(instance muggerScript of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(or gunDrawn toldMuggerToHalt calledKeith)
				local8
				(not muggerScared)
			)
			(= muggerScared 1)
			(= local12 1)
			(muggerScript changeState: 9)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 77)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gunFireState 3)
				(= local8 1)
				(if (!= (cSound state?) 3)
					(cSound number: 40 loop: 1 play:)
				)
				((= mugger (Actor new:))
					view: 52
					posn:
						(switch curRoomNum
							(79 142)
							(80 319)
							(81 0)
							(82 259)
						)
						(switch curRoomNum
							(79 219)
							(80 133)
							(81 185)
							(82 125)
						)
					init:
					setCycle: Walk
					setMotion: Chase ego 18 self
					setAvoider: (Avoider new:)
					setStep: 6 4
				)
				(if global236
					(switch curRoomNum
						(80
							(= egoX (+ (ego x?) 35))
							(= egoY (ego y?))
						)
						(81
							(= egoX (- (ego x?) 35))
							(= egoY (ego y?))
						)
						(82
							(= egoY (+ (ego y?) 35))
							(= egoX (ego x?))
						)
						(79
							(switch prevRoomNum
								(80
									(= egoX (- (ego x?) 35))
									(= egoY (ego y?))
								)
								(81
									(= egoX (+ (ego x?) 35))
									(= egoY (ego y?))
								)
								(else 
									(= egoX 40)
									(= egoY 125)
								)
							)
						)
					)
					(mugger posn: egoX egoY)
				)
				(if (not global236)
					(localproc_0036 77 32)
					(ego setMotion: 0)
					(localproc_0045 ego mugger)
					(= global236 1)
				)
				(= local3 1)
			)
			(1
				(= local9 1)
				(mugger view: 32)
				(self cue:)
			)
			(2
				(if (not (Btst 140))
					(localproc_0036 77 33 83)
					(Bset 140)
				)
				(ego setMotion: 0)
				(localproc_0045 ego mugger)
				(User canControl: 0)
				(= cycles 70)
			)
			(3
				(HandsOff)
				(localproc_0036 77 34 25 4)
				(self cue:)
			)
			(4
				(ego dispose:)
				(mugger hide:)
				(cSound fade:)
				((= newProp (Prop new:))
					view: 88
					loop: (if (< (ego x?) (mugger x?)) 0 else 1)
					init:
					posn: (ego x?) (ego y?)
					setCycle: EndLoop self
				)
			)
			(5
				(newProp loop: 2 setCycle: Forward)
				(mugMusic play: self)
			)
			(6
				((View new:)
					view: 88
					loop: 3
					cel: 0
					posn: (+ (newProp x?) 20) (newProp y?)
					init:
				)
				(mugger show:)
				(newProp dispose:)
				(= cycles 20)
			)
			(7
				(localproc_0036 77 35 83)
				(self cue:)
			)
			(8 (EgoDead 77 36))
			(9
				(User canControl: 1)
				(mugger
					view: 52
					setStep: 6 4
					setMotion:
						MoveTo
						(switch curRoomNum
							(79 142)
							(80 319)
							(81 0)
							(82
								(if (> (ego y?) 142) 121 else 259)
							)
						)
						(switch curRoomNum
							(79 219)
							(80 133)
							(81 167)
							(82
								(if (> (ego y?) 142) 215 else 125)
							)
						)
						self
				)
			)
			(10
				(if calledKeith
					(mugger hide:)
					(HandsOff)
					(User canInput: 1)
				else
					(= muggerFleeing 1)
					(mugger dispose:)
				)
				(= cycles 2)
			)
			(11
				(localproc_0036 77 37 83)
				(if calledKeith
					(= cycles 10)
				else
					(= local12 0)
					(= gunFireState 1)
					(= global236 0)
					(cSound fade:)
					(client setScript: 0)
				)
			)
			(12
				(localproc_0036 77 38 83 25 10)
				(self cue:)
			)
			(13
				(ego
					setMotion:
						MoveTo
						(switch curRoomNum
							(79 107)
							(80 217)
							(81 154)
							(82
								(if (> (ego y?) 150) 199 else 170)
							)
						)
						(switch curRoomNum
							(79 154)
							(80 151)
							(81 173)
							(82
								(if (> (ego y?) 150) 173 else 127)
							)
						)
						self
				)
			)
			(14
				(localproc_0045 ego mugger)
				(mugger
					posn:
						(switch curRoomNum
							(79 82)
							(80 324)
							(81 -9)
							(82
								(if (> (ego y?) 150) 86 else 261)
							)
						)
						(switch curRoomNum
							(79 234)
							(80 149)
							(81 170)
							(82
								(if (> (ego y?) 150) 232 else 130)
							)
						)
					view: 46
					setStep: 3 2
					setMotion:
						MoveTo
						(switch curRoomNum
							(79 97)
							(80 240)
							(81 126)
							(82
								(if (> (ego y?) 150) 172 else 189)
							)
						)
						(switch curRoomNum
							(79 164)
							(80 149)
							(81 170)
							(82
								(if (> (ego y?) 150) 169 else 130)
							)
						)
					show:
				)
				(= keithIsHere 1)
				(= cycles 10)
			)
			(15
				((= keith (Actor new:))
					view: 20
					illegalBits: 0
					posn:
						(switch curRoomNum
							(79 90)
							(80 324)
							(81 -9)
							(82
								(if (> (ego y?) 150) 83 else 261)
							)
						)
						(switch curRoomNum
							(79 238)
							(80 157)
							(81 178)
							(82
								(if (> (ego y?) 150) 233 else 124)
							)
						)
					init:
					setCycle: Walk
					setAvoider: (Avoider new:)
					setMotion:
						MoveTo
						(switch curRoomNum
							(79 112)
							(80 247)
							(81 136)
							(82
								(if (> (ego y?) 150) 182 else 200)
							)
						)
						(switch curRoomNum
							(79 172)
							(80 157)
							(81 178)
							(82
								(if (> (ego y?) 150) 175 else 124)
							)
						)
					ignoreActors:
					startUpd:
				)
				(= cycles 30)
			)
			(16
				(User canInput: 1)
				(localproc_0036 77 39 83)
				(= cycles 100)
			)
			(17
				(if (not local7)
					(localproc_0036 77 40 25 8)
					(localproc_0036 77 41)
					(localproc_0036 77 42 25 8)
					(self cue:)
				else
					(localproc_0036 77 42 25 10)
					(self cue:)
				)
			)
			(18
				(keith
					setMotion:
						MoveTo
						(switch curRoomNum
							(79 85)
							(80 324)
							(81 -9)
							(82
								(if (> (ego y?) 150) 83 else 261)
							)
						)
						(switch curRoomNum
							(79 234)
							(80 157)
							(81 178)
							(82
								(if (> (ego y?) 150) 233 else 124)
							)
						)
						self
				)
				(mugger
					setMotion:
						MoveTo
						(switch curRoomNum
							(79 78)
							(80 324)
							(81 -9)
							(82
								(if (> (ego y?) 150) 86 else 261)
							)
						)
						(switch curRoomNum
							(79 234)
							(80 149)
							(81 170)
							(82
								(if (> (ego y?) 150) 232 else 130)
							)
						)
				)
			)
			(19
				(= keithIsHere 0)
				(ego illegalBits: -32768)
				(keith dispose:)
				(mugger dispose:)
				(= gunFireState 1)
				(client setScript: 0)
				(= global236 0)
				(= muggerFleeing 1)
				(= muggerArrested 1)
				(cSound fade:)
				(HandsOn)
				(= muggerGone 1)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look>')
						(cond 
							((Said '/dude,mugger,punk,suspect,hair') (localproc_0036 77 0))
							((Said '/friend') (localproc_0036 77 1))
							((Said '/*') (localproc_0036 77 2))
						)
					)
					((Said 'chat/friend')
						(if keithIsHere
							(localproc_0036 77 3)
						else
							(localproc_0036 77 4)
						)
					)
					((Said 'freeze')
						(if (cast contains: mugger)
							(if (<= (mugger distanceTo: ego) 50)
								(= toldMuggerToHalt 1)
								(localproc_0036 77 5)
							else
								(localproc_0036 77 6)
							)
						else
							(localproc_0036 77 7)
						)
					)
					((Said 'display/badge,badge')
						(if
							(and
								(cast contains: mugger)
								(<= (mugger distanceTo: ego) 50)
							)
							(= toldMuggerToHalt 1)
							(localproc_0036 77 5)
						else
							(localproc_0036 77 8)
						)
					)
					(
						(or
							(Said 'call,extender/backup,friend')
							(Said '(key<up),use/(extender,talkie)')
						)
						(if (ego has: 30)
							(cond 
								(keithIsHere (localproc_0036 77 9))
								((and local3 (not muggerScared))
									(if (not calledKeith) (SolvePuzzle 5) (cSound stop:))
									(= calledKeith 1)
									(localproc_0036 77 10)
									(localproc_0036 77 11)
								)
								((and local3 muggerScared (not muggerFleeing))
									(if (not calledKeith) (SolvePuzzle 5) (cSound stop:))
									(= calledKeith 1)
									(localproc_0036 77 10)
								)
								(muggerGone (localproc_0036 77 12))
								(muggerFleeing
									(switch (Random 1 4)
										(1
											(localproc_0036 77 13)
											(localproc_0036 77 14)
											(localproc_0036 77 15)
										)
										(2 (localproc_0036 77 16))
										(3 (localproc_0036 77 17))
										(4 (localproc_0036 77 18))
									)
								)
								((not muggerFleeing) (localproc_0036 77 19))
							)
						else
							(localproc_0036 77 20)
						)
					)
					((Said 'extender/dispatch') (localproc_0036 77 21))
					(
					(Said 'arrest,book,arrest/dude,mugger,punk,suspect')
						(if keithIsHere
							(localproc_0036 77 22)
						else
							(localproc_0036 77 23)
						)
					)
					((Said 'read,gave/right,miranda')
						(if keithIsHere
							(if (not readMuggerRights)
								(SolvePuzzle 2)
								(= readMuggerRights 1)
							)
							(localproc_0036 77 24)
						else
							(localproc_0036 77 25)
						)
					)
					((Said 'gave/cash')
						(cond 
							((or (not local3) (not muggerFleeing)) (localproc_0036 77 25))
							((and local3 (not muggerScared)) (localproc_0036 77 26))
							(else (localproc_0036 77 27))
						)
					)
					(
					(Said 'interrogate,chat/dude,mugger,punk,suspect')
						(cond 
							((not local3) (localproc_0036 77 25))
							(muggerGone (localproc_0036 77 28))
							((and keithIsHere (not local7))
								(if (not local17) (= local17 1) (SolvePuzzle 2))
								(localproc_0036 77 29)
								(= local7 1)
							)
							((and keithIsHere local7) (localproc_0036 77 30))
							((and local3 (not keithIsHere)) (localproc_0036 77 31))
						)
					)
				)
			)
		)
	)
)
