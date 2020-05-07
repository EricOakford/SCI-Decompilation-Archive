;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include game.sh)
(use Main)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm37 0
)

(local
	gateOpen
	local1
	karlCantSeeEgo
	karlLoop
	local4
	karlResumedPatral
	local6
	crushedByGate
	local8
	local9
	climbFail
	karlPatrolling
	invitedIntoCastle
)
(procedure (KarlStopsToTalk &tmp temp0)
	(cond 
		((not local1) (guard setScript: guardStopToTalk) (= temp0 0))
		(local4 (= temp0 1))
		(1 (= local4 1) (= temp0 1))
		(else
			(HighPrint 37 0)
			;"Please step where I can see you."
			(= temp0 0))
	)
	(return temp0)
)

(instance rm37 of Room
	(properties
		picture 37
		style HSHUTTER
	)
	
	(method (init)
		(super init:)
		(if Night (Load VIEW vEgoDefeated) (Load VIEW vEgoClimbing))
		(Load VIEW vCastleGate)
		(Load SOUND (SoundFX 90))
		(lBush init: addToPic:)
		(rBush init: addToPic:)
		(portSound number: (SoundFX 90) init:)
		(mouseDownHandler add: self)
		(self
			setFeatures: onCastle onCastleGate onLGateHouse onMountain
		)
		(StatusLine enable:)
		(NormalEgo)
		(HandsOff)
		(switch prevRoomNum
			(39
				(if Night
					(castleGate init: stopUpd:)
					(ego setScript: egoClimbsDown)
				else
					(portSound play:)
					(castleBars x: 160 y: 127 init: stopUpd:)
					(= gateOpen TRUE)
					(= karlLoop 4)
					(guard loop: 4 cel: 1 illegalBits: 0 x: 158 y: 77 init:)
					(ego setScript: egoLeavesCastle)
				)
			)
			(else 
				(if Night
					(castleGate init: stopUpd:)
				else
					(if
					(or (and (Btst SAVED_BARNARD)
							(not (Btst GOT_KARL_ATTENTION)
								)
							)
								(Btst SAVED_ELSA))
						(= invitedIntoCastle TRUE)
						(guard init: setScript: guardGreets)
						(Bset GOT_KARL_ATTENTION)
					else
						(guard init: setScript: guardPatrols)
					)
					(castleBars init: stopUpd:)
				)
				(ego posn: 161 233 init: setScript: normalEntry)
			)
		)
	)
	
	(method (doit)
		(if (and (< (ego y?) 189) (not local9)) (= local9 1))
		(cond 
			((and (Btst SAVED_ELSA) gateOpen (< (ego y?) 163)) (curRoom newRoom: ENDGAME))
			((and gateOpen (< (ego y?) 163)) (curRoom newRoom: 39))
			((and local9 (>= (ego y?) 189)) (curRoom newRoom: 54))
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'open,lift/gate,portcullis,door')
						(HighPrint 37 1)
						;There is nobody to open the portcullis.
						)
					((Said 'climb,climb[/gate,wall,gatehouse]')
						(if Night
							(cond 
								((< [egoStats CLIMB] 10)
									(HighPrint 37 2)
									;Leave the climbing to those who know how.
									)
								((> [egoStats CLIMB] 35)
									(if
										(or
											(and
												(<= 78 (ego x?))
												(<= (ego x?) 114)
												(<= 160 (ego y?))
												(<= (ego y?) 182)
											)
											(and
												(<= 34 (ego x?))
												(<= (ego x?) 65)
												(<= 160 (ego y?))
												(<= (ego y?) 182)
											)
										)
										(= local8 0)
										(self setScript: egoClimbsWall)
									else
										(HighPrint 37 3)
										;You're not in a good place to climb.
									)
								)
								(
									(or
										(and
											(<= 78 (ego x?))
											(<= (ego x?) 114)
											(<= 160 (ego y?))
											(<= (ego y?) 182)
										)
										(and
											(<= 34 (ego x?))
											(<= (ego x?) 65)
											(<= 160 (ego y?))
											(<= (ego y?) 182)
										)
									)
									(= local8 (+ local8 1))
									(self setScript: egoClimbsWall)
								)
								(else (HighPrint 37 3)
									;You're not in a good place to climb.
									)
							)
						else
							(HighPrint 37 4)
							;The guards won't allow you to do that.
						)
					)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/castle,building]')
								(HighPrint 37 5)
								;The castle looms over the gatehouse and looks rather impressive.
								)
							((Said '[<at,around]/gate,gatehouse,wall')
								(HighPrint 37 6)
								;The gatehouse is a massive structure with a portcullis closing off access to the castle.
								)
							((Said '[<at,around]/tower')
								(HighPrint 37 7)
								;The towers stand out above the castle walls.
								)
							((or (Said '<up') (Said '/sky'))
								(HighPrint 37 8)
								;The sky is clear except for a few clouds.
								)
							((or (Said '<down') (Said '/ground,grass'))
								(HighPrint 37 9)
								;The grass seems to grow well here.
								)
							((Said '/south')
								(HighPrint 37 10)
								;You see the road leading past the Healer's house.
								)
							((Said '/east,west')
								(HighPrint 37 11)
								;You see the forest.
								)
						)
					)
				)
			)
		)
	)
)

(instance guardPatrols of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local1 0)
				(guard
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: Forward
					illegalBits: 0
					setMotion: MoveTo 277 77 self
				)
				(= karlLoop 4)
			)
			(1 (= cycles 2))
			(2
				(guard loop: 2 cel: 0 cycleSpeed: 3 setCycle: EndLoop self)
			)
			(3 (= cycles 1))
			(4
				(guard
					loop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: Forward
					setMotion: MoveTo 43 77 self
				)
				(= karlLoop 5)
			)
			(5 (= cycles 2))
			(6
				(guard loop: 2 cel: 2 cycleSpeed: 3 setCycle: BegLoop self)
			)
			(7 (= cycles 1))
			(8 (self changeState: 0))
		)
	)
)

(instance guardGreets of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= karlLoop 4)
				(guard
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: Forward
					illegalBits: 0
					setMotion: MoveTo 161 77 self
				)
			)
			(1
				(guard setMotion: 0 setCycle: 0)
				(= cycles 3)
			)
			(2
				(guard
					loop: karlLoop
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(3 (= cycles 2))
			(4
				(HighPrint 37 12)
				;"Come in.  Come in.  You have been expected.  Baron von Spielburg awaits you in the castle."
				(= local1 1)
				(= cycles 2)
			)
			(5
				(castleBars setScript: openGate)
				(= seconds 3)
			)
			(6
				(if (Btst SAVED_ELSA)
					(curRoom newRoom: ENDGAME)
				else
					(HandsOn)
					(client setScript: timeout)
				)
			)
		)
	)
)

(instance guardStopToTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guard setMotion: 0 setCycle: 0)
				(= cycles 3)
			)
			(1
				(guard
					loop: karlLoop
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(2 (= cycles 2))
			(3
				(HandsOn)
				(Face ego guard)
				(if (or (> (ego y?) 170) karlCantSeeEgo)
					(HighPrint 37 13)
					;"Are you speaking to me?"
					(= karlCantSeeEgo FALSE)
				else
					(HighPrint 37 14)
					;"Who's there? Come out and show yourself!"
					(= karlCantSeeEgo TRUE)
				)
				(= local1 1)
				(client setScript: timeout)
			)
		)
	)
)

(instance timeout of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and karlCantSeeEgo (> (ego y?) 172))
			(client setScript: guardStopToTalk)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 12))
			(1
				(= karlCantSeeEgo FALSE)
				(if local4
					(= local4 0)
					(self changeState: 0)
				else
					(self cue:)
				)
			)
			(2
				(if gateOpen
					(HighPrint 37 15)
					;"Well?  Enter the castle grounds!"
					(= karlResumedPatral TRUE)
					(= seconds 5)
				else
					(client setScript: guardResumesPatrol)
				)
			)
			(3
				(if local4
					(= local4 0)
					(self changeState: 0)
				else
					(= karlResumedPatral TRUE)
					(client setScript: guardResumesPatrol)
				)
			)
		)
	)
)

(instance guardResumesPatrol of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if gateOpen
					(castleBars setScript: closeGate)
				else
					(self cue:)
				)
			)
			(1
				(= local6 karlLoop)
				(guard
					loop: local6
					cel: 1
					cycleSpeed: 4
					setCycle: BegLoop self
				)
			)
			(2
				(if (== local6 4)
					(guard
						loop: 0
						cel: 0
						cycleSpeed: 1
						setCycle: Forward
						illegalBits: 0
						setMotion: MoveTo 277 77 self
					)
					(= karlLoop 4)
				else
					(guard
						loop: 1
						cel: 0
						cycleSpeed: 1
						setCycle: Forward
						setMotion: MoveTo 43 77 self
					)
					(= karlLoop 5)
				)
				(= local1 0)
				(HandsOn)
			)
			(3 (= cycles 2))
			(4
				(if (== local6 4)
					(guard loop: 2 cel: 0 cycleSpeed: 3 setCycle: EndLoop self)
				else
					(guard loop: 2 cel: 2 cycleSpeed: 3 setCycle: BegLoop self)
				)
			)
			(5 (= cycles 1))
			(6
				(if (== local6 4)
					(guard
						loop: 1
						cel: 0
						cycleSpeed: 1
						setCycle: Forward
						setMotion: MoveTo 43 77 self
					)
					(= karlLoop 5)
				else
					(client setScript: guardPatrols)
				)
			)
			(7 (= cycles 2))
			(8
				(guard loop: 2 cel: 2 cycleSpeed: 3 setCycle: BegLoop self)
			)
			(9 (= cycles 1))
			(10
				(client setScript: guardPatrols)
			)
		)
	)
)

(instance openGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (timeout seconds?) (timeout seconds: 12))
				(cond 
					((and (< 123 (guard x?)) (< (guard x?) 198)) (self changeState: 3))
					((< (guard x?) 160) (guard loop: 4 cel: 1 cycleSpeed: 4 setCycle: BegLoop self))
					(else (guard loop: 5 cel: 1 cycleSpeed: 4 setCycle: BegLoop self))
				)
			)
			(1
				(if (< (guard x?) 160)
					(guard
						loop: 0
						cel: 0
						cycleSpeed: 1
						setCycle: Forward
						illegalBits: 0
						setMotion: MoveTo 160 77 self
					)
					(= karlLoop 4)
				else
					(guard
						loop: 1
						cel: 0
						cycleSpeed: 1
						setCycle: Forward
						setMotion: MoveTo 160 77 self
					)
					(= karlLoop 5)
				)
			)
			(2
				(guard
					loop: karlLoop
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(3 (= cycles 2))
			(4
				(portSound play:)
				(castleBars
					illegalBits: 0
					setLoop: 7
					xStep: 1
					yStep: 1
					moveSpeed: 1
					setMotion: MoveTo (castleBars x?) 127 self
				)
			)
			(5
				(= gateOpen TRUE)
				(castleBars stopUpd:)
				(HandsOn)
			)
		)
	)
)

(instance closeGateSafe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 8))
			(1
				(portSound play:)
				(castleBars
					illegalBits: 0
					setLoop: 7
					xStep: 1
					yStep: 1
					moveSpeed: 1
					setMotion: MoveTo (castleBars x?) 155 self
				)
			)
			(2
				(HandsOn)
				(castleBars stopUpd:)
				(= gateOpen FALSE)
				(guardResumesPatrol cue:)
			)
		)
	)
)

(instance closeGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						(< 145 (ego x?))
						(< (ego x?) 175)
						(< 0 (ego y?))
						(< (ego y?) 173)
					)
					(HandsOff)
					(ego setMotion: MoveTo 160 166 self)
				else
					(client setScript: closeGateSafe)
				)
			)
			(1
				(NormalEgo)
				(ego loop: 2)
				(= cycles 1)
			)
			(2
				(portSound play:)
				(castleBars
					illegalBits: 0
					setLoop: 7
					xStep: 1
					yStep: 1
					moveSpeed: 1
					setMotion: MoveTo (castleBars x?) 160 self
				)
				(ego
					view: vEgoDefeated
					loop: 0
					cel: 0
					illegalBits: 0
					moveSpeed: 3
					cycleSpeed: 4
					xStep: 1
					yStep: 1
					setCycle: EndLoop
				)
			)
			(3
				(castleBars stopUpd:)
				(= gateOpen FALSE)
				(if crushedByGate
					(HighPrint 37 16)
					;Once more the guard raises the portcullis.
					else
					(HighPrint 37 17)
					;The guard, sensing something is awry, raises the portcullis.
					)
				(self cue:)
			)
			(4
				(castleBars
					illegalBits: 0
					setLoop: 7
					xStep: 1
					yStep: 1
					moveSpeed: 1
					setMotion: MoveTo (castleBars x?) 127 self
				)
			)
			(5
				(= gateOpen TRUE)
				(castleBars stopUpd:)
				(self cue:)
			)
			(6 (= seconds 3))
			(7
				(if crushedByGate
					(EgoDead 37 18
						#title {OH NOOOOOOO!}
						#icon vDeathScenes 0 0) ;EO - "known" should be "know".
						;You're not a quick learner.  Now you known why the grass grows so well around the portcullis.
				else
					(ego
						view: vEgoDefeated
						loop: 4
						cel: 0
						illegalBits: 0
						moveSpeed: 1
						setCycle: EndLoop self
					)
					(= crushedByGate TRUE)
				)
			)
			(8
				(HighPrint 37 19)
				;That smarts.  Maybe you should move a little more quickly.
				(NormalEgo)
				(ego loop: 2)
				(HandsOn)
				(self cue:)
			)
			(9 (= seconds 5))
			(10 (self changeState: 0))
		)
	)
)

(instance egoClimbsWall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo (ego x?) 173 self)
			)
			(1
				(ego
					view: vEgoClimbing
					setLoop: 0
					cel: 0
					setPri: 13
					xStep: 0
					yStep: 8
					cycleSpeed: 2
					moveSpeed: 2
					setCycle: Forward
					illegalBits: 0
					setMotion: MoveTo (ego x?) 114 self
				)
			)
			(2
				(ego
					view: vEgoClimbing
					setLoop: 1
					cel: 0
					setPri: 13
					illegalBits: 0
					x: (- (ego x?) 12)
					y: (- (ego y?) 33)
					xStep: 0
					yStep: 4
					cycleSpeed: 3
					moveSpeed: 3
					setCycle: EndLoop self
				)
			)
			(3 (= cycles 10))
			(4
				(if local8
					(ego
						view: vEgoClimbing
						setLoop: 2
						cel: 0
						setPri: 13
						xStep: 0
						yStep: 5
						cycleSpeed: 3
						moveSpeed: 0
						setCycle: EndLoop
						illegalBits: 0
						setMotion: MoveTo (ego x?) 145 self
					)
				else
					(curRoom newRoom: 39)
				)
			)
			(5
				(if (not (TakeDamage 10))
					(EgoDead 37 20
						#icon vEgoClimbing 2 5
						#title {Your figure remains still and silent.}
						;Had you been healthier, you probably could have survived that fall.
						;In your weakened condition, however, you succumbed to your injuries.
					)
				else
					(ego
						view: vEgoDefeated
						setLoop: 4
						cel: 0
						x: (+ (ego x?) 19)
						y: (+ (ego y?) 18)
						setPri: 13
						illegalBits: cWHITE
						setCycle: 0
					)
					(switch climbFail
						(0
							(HighPrint 37 21)
							;Experience is the best teacher.
							)
						(1
							(HighPrint 37 22)
							;Practice makes perfect.
							)
						(2
							(HighPrint 37 23)
							;Try, try again etc...
							)
						(3
							(HighPrint 37 24)
							;Take a break.  It's Mueller time.
							)
						(4
							(HighPrint 37 25)
							;The gatehouse is more difficult to climb than it first appeared.
							)
						(else
							(HighPrint 37 26)
							;Remember what happened to Humpty Dumpty.
							)
					)
					(++ climbFail)
					(self cue:)
				)
			)
			(6
				(ego moveSpeed: 1 setCycle: EndLoop self)
			)
			(7 (HandsOn) (NormalEgo))
		)
	)
)

(instance egoClimbsDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: vEgoClimbing
					setLoop: 0
					cel: 0
					setPri: 13
					x: 91
					y: 114
					init:
					illegalBits: 0
				)
				(= cycles 2)
			)
			(1
				(ego
					xStep: 0
					yStep: 8
					cycleSpeed: 2
					moveSpeed: 2
					setCycle: Forward
					setMotion: MoveTo (ego x?) 170 self
				)
			)
			(2
				(NormalEgo)
				(ego
					illegalBits: 0
					loop: 3
					setMotion: MoveTo (ego x?) 180 self
				)
			)
			(3
				(NormalEgo)
				(HandsOn)
				(HighPrint 37 27)
				;After climbing down the wall you says to yourself, "Self", you says, "that wasn't too bad."
			)
		)
	)
)

(instance egoLeavesCastle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego init: posn: 161 169 setMotion: MoveTo 161 187)
				(= cycles 20)
			)
			(1
				(HandsOn)
				(guard setScript: guardResumesPatrol)
			)
		)
	)
)

(instance guard of Actor
	(properties
		y 77
		x 43
		view vCastleGate
		cycleSpeed 1
		xStep 2
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(mouseDown
				(if (MouseClaimed guard event shiftDown)
					(HighPrint 37 28)
					;You can't see much of the gatekeeper over the battlements.
				)
			)
			(saidEvent
				(cond 
					((super handleEvent: event))
					(
						(or
							(Said 'ask//gate,portcullis,door<open')
							(Said 'open,lift/gate,portcullis,door')
						)
						(if (KarlStopsToTalk)
							(cond 
								(gateOpen
									(HighPrint 37 29)
									;"As you can see, the gate is open."
									)
								(karlCantSeeEgo
									(HighPrint 37 30)
									;"I will not open for a faceless voice. Come out where I can see you."
									)
								(else
									(HighPrint 37 31)
									;"Very well."
									(castleBars setScript: openGate))
							)
						)
					)
					((Said 'close,close,lower/gate,portcullis,door')
						(if (KarlStopsToTalk)
							(if gateOpen
								(HighPrint 37 32)
								;"If you wish."
								(castleBars setScript: closeGate)
							else
								(HighPrint 37 33)
								;"As you can see, the gate is closed."
							)
						)
					)
					(invitedIntoCastle
						(HighPrint 37 34)
						;Don't keep everybody waiting with idle talk.
						(event claimed: 1))
					((Said '/hey')
						(if (KarlStopsToTalk)
							(HighPrint 37 35)
							;"Hay is for horses."
							)
						)
					(
					(or (Said '/hello') (Said 'call/guard,man,gatekeeper'))
					(if (KarlStopsToTalk)
						(HighPrint 37 36)
						;"I'm here!  What do you want?"
						)
					)
					((Said 'say') (if (KarlStopsToTalk)
							(HighPrint 37 37)
							;"What?"
							)
						)
					((Said 'affirmative,please')
						(if (KarlStopsToTalk)
							(if karlResumedPatral
								(HighPrint 37 38)
								;"Then go, before I close the portcullis."
									else
									(HighPrint 37 39)
									;"Sorry.  I wasn't listening.  Could you repeat youself?"
									)
						)
					)
					((Said 'n')
						(if (KarlStopsToTalk)
							(HighPrint 37 40)
							;"OK."
							(guard setScript: guardResumesPatrol)
						)
					)
					((Said 'look/guard,man')
						(HighPrint 37 28)
						;You can't see much of the gatekeeper over the battlements.
						)
					((Said 'chat[<to]/guard,man')
						(HighPrint 37 41)
						;Go ahead.
						)
					((Said 'chat,ask>')
						(= karlPatrolling (KarlStopsToTalk))
						(cond 
							((Said '//name,handle') (if (KarlStopsToTalk) (= karlPatrolling FALSE)
									(HighPrint 37 42)
									;"My name is Karl."
									)
								)
							((Said '//karl')
								(if (KarlStopsToTalk)
									(= karlPatrolling FALSE)
								else
									(HighPrint 37 43)
									;"That's me. I guard the portcullis day after day."
								)
							)
							(
								(or
									(Said '//in<go,come')
									(Said '//portcullis,gate,door<open,lift')
								)
								(if (KarlStopsToTalk)
									(= karlPatrolling FALSE)
									(if gateOpen
										(HighPrint 37 29)
										;"As you can see, the gate is open."
									else
										(HighPrint 37 31)
										;"Very well."
										(castleBars setScript: openGate)
									)
								)
							)
							((Said '//gate,portcullis') (if (KarlStopsToTalk) (= karlPatrolling FALSE)
									(HighPrint 37 44)
									;"The portcullis is the metal grate that bars the entrance to the castle yard.
									)
								)
							((Said '//castle,building') (if (KarlStopsToTalk)
									(HighPrint 37 45)
									;"This is the Castle of the Baron Stefan von Spielburg, Lord of this Valley."
									)
								)
							((Said '//lord,baron,hamlet')
								(if (KarlStopsToTalk)
									(if (Btst SAVED_BARNARD)
										(HighPrint 37 46)
										;"My Lord is busy organizing his troops."
									else
										(HighPrint 37 47)
										;"My Lord sees no one since the loss of his son and daughter."
									)
								)
							)
							((Said '//loss')
								(if (KarlStopsToTalk)
									(HighPrint 37 48)
									;"Ten years ago the Baron's daughter, Elsa, was stolen away by magic.
									;Five years back, the Baronet Barnard von Spielburg went on a hunt from which he did not return."
									(HighPrint 37 49)
									;"This was all clearly part of Baba Yaga's curse."
								)
							)
							((Said '//barnard,barnard')
								(if (KarlStopsToTalk)
									(if (Btst SAVED_BARNARD)
										(HighPrint 37 50)
										;"The Baronet is busy hibernating."
									else
										(HighPrint 37 51)
										;"The Baronet Barnard von Spielburg was a proud young man with the dark hair and eyes of his father, the Baron."
										(HighPrint 37 52)
										;"He rode off one morning and his horse returned without him.  The horse had been raked by the claws of some large animal.
										;There is still a reward offered for the safe return of the Baronet, but I fear he is dead."
									)
								)
							)
							((Said '//daughter,elsa')
								(if (KarlStopsToTalk)
									(HighPrint 37 53)
									;"Elsa von Spielburg was a lovely young child with braided brown hair and bright blue eyes,
									;like her mother, the Baroness, used to have."
									(HighPrint 37 54)
									;"A large winged creature flew over the castle walls, grabbed Elsa, and vanished before the guards could fire upon it.
									;We searched everywhere for her, but to no avail."
									(HighPrint 37 55)
									;"Even the jester, Yorick, joined the search for her.
									;The Baron sent a troup of guards to storm Baba Yaga's hut, but I think their skulls sit atop her fence now."
									(HighPrint 37 56)
									;"Elsa von Spielburg would be about eighteen now."
								)
							)
							((Said '//job,labor,labor') (if (KarlStopsToTalk)
									(HighPrint 37 57)
									;"The stablekeeper could use a strong young person to help clean out the stable.
									;It does not pay much, but it is good, honest work.
									;If you would like to take the job, just ask me to open the portcullis."
									)
								)
							((Said '//stable') (if (KarlStopsToTalk)
									(HighPrint 37 58)
									;"Cleaning up after the horses is a good way to build up your muscles."
									)
								)
							((Said '//misfortune,curse')
								(if (KarlStopsToTalk)
									(HighPrint 37 59)
									;"The Baron ordered Baba Yaga to leave and she first cursed the graveyard.
									;When he then sent the Baronial Guards after her, only the captain returned to the castle."
									(HighPrint 37 60)
									;"He spoke the words, 'Hear me, oh mighty Baron.
									;You have angered me, and thus you are cursed.
									;All that you value you shall lose;
									;all that you treasure shall be taken from you.'
									;The captain then dissolved into a pool of sticky blackness."
								)
							)
							((Said '//jester,yorick') (if (KarlStopsToTalk)
									(HighPrint 37 61)
									;"He was a very jolly fool who loved to laugh.
									;He was devoted to Elsa and swore he would not return until Elsa was safe."
									)
								)
							((Said '//baba,baba,ogress') (if (KarlStopsToTalk)
									(HighPrint 37 62)
									;"The powerful Ogress Baba Yaga is the cause of all the Baron's misfortune.
									;She is a vile creature who knows much magic."
									)
								)
							((Said '//prize')
								(if (KarlStopsToTalk)
									(HighPrint 37 63)
									;"There is still a reward of 50 gold offered for the safe return of the Baronet and Elsa von Spielburg."
									(HighPrint 37 64)
									;"For the capture or death of the brigand Leader there is the reward of 60 gold
									;and the title of 'Hero of the Realm of Spielburg'."
								)
							)
							((Said '//warlock[<bandit,about]') (if (KarlStopsToTalk)
									(HighPrint 37 65)
									;"The brigands are protected by a magic user.
									;Not much is known about him, but he seems to use more magic potions and powders than spells."
									)
								)
							((Said '//leader[<bandit,about]') (if (KarlStopsToTalk)
									(HighPrint 37 66)
									;"The leader of the brigands must be a good strategist.
									;He has organized and led many raids which result in much treasure and little loss on the part of the brigands.
									;Without their leader, the brigands would soon flee."
									)
								)
							((Said '//bandit') (if (KarlStopsToTalk)
									(HighPrint 37 67)
									;"There are now too few guards to take out the brigands, who still rob our people and drive away the traders.
									;They hide somewhere near the edge of this valley."
									)
								)
							((Said '//loot,robbery,stealing') (if (KarlStopsToTalk)
									(HighPrint 37 68)
									;"This was once a very wealthy valley.  Many merchants have been robbed by the brigands.
									;There is a merchant in town who could tell you about his recent loss."
									)
								)
							((Said '//cemetery,cemetery') (if (KarlStopsToTalk)
									(HighPrint 37 69)
									;"Baba Yaga cursed the graveyard such that the dead can not rest at night.
									;One cannot go near the graveyard at dark without risking one's own death unless one uses protection."
									)
								)
							((Said '//protection') (if (KarlStopsToTalk)
									(HighPrint 37 70)
									;"There is an unguent you can purchase from the Healer which protects against the dead that walk at night."
									)
								)
							((Said '//hero') (if (KarlStopsToTalk)
									(HighPrint 37 71)
									;"'Hero of the Realm of Spielburg' is a title of respect and honor.
									;A true hero can release this land from its curse by using intelligence and courage."
									)
								)
							((Said '//monster,creature,animal') (if (KarlStopsToTalk)
									(HighPrint 37 72)
									;"There are many foul creatures stalking the forest.
									;The Baron has too few guards to protect this valley from them."
									)
								)
							((Said '//valley') (if (KarlStopsToTalk)
									(HighPrint 37 73)
									;"Spielburg Valley was once a prosperous and peaceful place before the Baron lost his family.
									;Now the Baron will not leave his castle and has no idea of the ruin this land has come to."
									)
								)
							((Said '//hut') (if (KarlStopsToTalk)
									(HighPrint 37 74)
									;"Baba Yaga has her hut to the northwest.  It is a very dangerous place and many Baronial Guards were lost there."
									)
								)
							((Said '//baroness') (if (KarlStopsToTalk) (= karlPatrolling FALSE)
									(HighPrint 37 75)
									;"The Baroness died soon after the birth of her daughter.  The Baron still mourns for her."
									)
								)
							((Said '//guard') (if (KarlStopsToTalk) (= karlPatrolling FALSE)
									(HighPrint 37 76)
									;"The Baron von Spielburg lost most of his guards trying to defeat Baba Yaga."
									)
								)
							((Said '//gatehouse') (if (KarlStopsToTalk) (= karlPatrolling FALSE)
									(HighPrint 37 77)
									;"The gatehouse is a shelter and resting place for the guards."
									)
								)
							((Said '//weather') (if (KarlStopsToTalk) (= karlPatrolling FALSE)
									(HighPrint 37 78)
									;"Everybody talks about the weather, but nobody does anything about it."
									)
								)
							((Said '//(master[<weapon,about]),fight,practice') (if (KarlStopsToTalk)
								(HighPrint 37 79)
								;"The Weapons Master often practices in the castle courtyard.  He gives lessons in the art of swordplay.
								;The lessons are expensive, but well worth the price."
								)
							)
							((and (Said '//*') (KarlStopsToTalk)) (= karlPatrolling FALSE)
								(HighPrint 37 80)
								;"I wouldn't know about that, I don't get around much these days."
								)
						)
						(if karlPatrolling (SolvePuzzle POINTS_TALKTOGATEKEEPER 5))
					)
				)
			)
		)
	)
)

(instance castleBars of Actor
	(properties
		y 155
		x 160
		view vCastleGate
		loop 7
	)
)

(instance castleGate of View
	(properties
		y 167
		x 160
		view vCastleGate
		loop 6
	)
)

(instance lBush of View
	(properties
		y 189
		x 10
		view vCastleGate
		loop 8
	)
)

(instance rBush of View
	(properties
		y 189
		x 310
		view vCastleGate
		loop 9
	)
)

(instance onCastleGate of RFeature
	(properties
		nsTop 122
		nsLeft 133
		nsBottom 168
		nsRight 184
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onCastleGate event shiftDown)
				(cond 
					(Night
						(HighPrint 37 81)
						;The castle is securely locked for the night.
						)
					(gateOpen
						(HighPrint 37 82)
						;The portcullis has been drawn, and the way to the castle is clear.
						)
					(else
						(HighPrint 37 83)
						;The portcullis presents a severe obstacle for entry to the castle.
						)
				)
			)
		)
	)
)

(instance onCastle of RFeature
	(properties
		nsTop 16
		nsLeft 85
		nsBottom 70
		nsRight 234
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onCastle event shiftDown)
				(HighPrint 37 84)
				;The castle of the Baron Stefan von Spielburg, Lord of the valley.
				)
		)
	)
)

(instance onLGateHouse of RFeature
	(properties
		nsTop 35
		nsBottom 125
		nsRight 59
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed onLGateHouse event shiftDown)
					(MouseClaimed onRGateHouse event shiftDown)
				)
				(cond 
					(Night
						(HighPrint 37 85)
						;The gatehouse is a massive structure which houses the guards.
						)
					(gateOpen
						(HighPrint 37 86)
						;The gatehouse is a massive structure.
						)
					(else
						(HighPrint 37 6)
						;The gatehouse is a massive structure with a portcullis closing off access to the castle.
						)
				)
			)
		)
	)
)

(instance onRGateHouse of RFeature
	(properties
		nsTop 41
		nsLeft 255
		nsBottom 126
		nsRight 318
	)
)

(instance onMountain of RFeature
	(properties
		nsTop 31
		nsLeft 60
		nsBottom 54
		nsRight 83
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(MouseClaimed onMountain event shiftDown)
					(MouseClaimed onM1 event shiftDown)
					(MouseClaimed onM2 event shiftDown)
					(MouseClaimed onM3 event shiftDown)
				)
				(HighPrint 37 87)
				;The mountains in the distance ring the valley of Spielburg, creating a formidable barrier to travelers.
			)
		)
	)
)

(instance onM1 of RFeature
	(properties
		nsTop 35
		nsLeft 235
		nsBottom 55
		nsRight 253
	)
)

(instance onM2 of RFeature
	(properties
		nsTop 29
		nsLeft 254
		nsBottom 37
		nsRight 280
	)
)

(instance onM3 of RFeature
	(properties
		nsTop 21
		nsLeft 281
		nsBottom 30
		nsRight 315
	)
)

(instance portSound of Sound
	(properties
		number 90
		priority 8
	)
)

(instance normalEntry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 161 187)
				(= cycles 30)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)
