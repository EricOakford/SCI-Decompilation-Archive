;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm17 0
)

(local
	escalator1
	escalator2
	local2
	local3
	local4
	scruffy
	local6
	local7
	local8
	local9
	local10
	local11
)
(procedure (localproc_000c)
	(Print &rest #at -1 15)
)

(instance rm17 of Rm
	(properties
		picture 17
		style $0000
	)
	
	(method (init)
		(super init:)
		(= perspective 70)
		(User canInput: 1 canControl: 1)
		(= gunFireState 3)
		(= gunNotNeeded 1)
		(Load rsVIEW 1)
		(Load rsVIEW 20)
		(Load rsVIEW 78)
		(Load rsVIEW 77)
		((= scruffy (Act new:))
			view: 78
			setLoop: 1
			init:
			illegalBits: 0
			setScript: scruffyScript
		)
		((View new:)
			view: 78
			loop: 0
			cel: 2
			posn: 196 96
			init:
			setPri: 6
			addToPic:
		)
		((View new:)
			view: 78
			loop: 0
			cel: 3
			posn: 178 96
			init:
			setPri: 6
			addToPic:
		)
		((= escalator1 (Prop new:))
			view: 78
			loop: 3
			posn: 80 125
			setPri: 5
			setCycle: Fwd
			cycleSpeed: 1
			ignoreActors:
			init:
		)
		((= escalator2 (Prop new:))
			view: 78
			loop: 4
			posn: 54 139
			setPri: 9
			setCycle: Rev
			cycleSpeed: 1
			init:
			ignoreActors:
		)
		((View new:)
			view: 78
			loop: 0
			cel: 0
			posn: 118 89
			setPri: 7
			init:
			addToPic:
		)
		((View new:)
			view: 78
			loop: 0
			cel: 1
			posn: 159 112
			setPri: 8
			init:
			addToPic:
		)
		((View new:)
			view: 78
			loop: 0
			cel: 4
			posn: 167 93
			init:
			setPri: 7
			addToPic:
		)
		((View new:)
			view: 78
			loop: 0
			cel: 5
			posn: 197 93
			init:
			setPri: 7
			addToPic:
		)
		((View new:)
			view: 78
			loop: 0
			cel: 6
			posn: 158 119
			init:
			addToPic:
		)
		((View new:)
			view: 77
			loop: 8
			cel: 0
			posn: 267 116
			init:
			addToPic:
		)
		((View new:)
			view: 77
			loop: 8
			cel: 1
			posn: 282 115
			init:
			addToPic:
		)
		(self setLocales: 153)
		(self setScript: rm17Script)
	)
	
	(method (dispose)
		(scruffyScript dispose:)
		(agentScript dispose:)
		(super dispose:)
	)
)

(instance rm17Script of Script
	(properties)
	
	(method (doit)
		(cond 
			((>= (ego x?) 320) (= perspective 0) (curRoom newRoom: 16))
			((and (< (ego x?) 31) (> (ego y?) 150))
				(localproc_000c 17 0)
				(ego setMotion: MoveTo 150 (ego y?))
			)
			((> (ego y?) 180)
				(localproc_000c 17 0)
				(ego setMotion: MoveTo (ego x?) 150)
			)
			(
			(and local11 (not local9) (== (ego onControl: 1) 8192)) (localproc_000c 17 1) (= local9 1) (ego cel: 6))
			((and local9 (!= (ego onControl: 1) 8192)) (= local9 0))
			((and (== (ego onControl: 1) 16384) local11) (= local10 1) (self changeState: 5))
		)
		(cond 
			((ego inRect: 134 116 185 124) (if (!= local4 1) (= local4 1)))
			((ego inRect: 185 116 226 124) (if (!= local4 2) (= local4 2)))
			(else (= local4 0))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SL enable:)
				(if (== prevRoomNum 16)
					(User prevDir: 7)
					(ego
						view: (if gunDrawn 7 else 1)
						posn: 318 145
						init:
						setMotion: MoveTo 50 145
					)
					(= local11 1)
					(if (Btst 40)
						((= keith (Act new:))
							view: 20
							loop: 0
							cel: 7
							posn: (+ (ego x?) 42) (ego y?)
							setAvoider: (Avoid new:)
							setCycle: Walk
							illegalBits: -28672
							init:
							setMotion: Follow ego 40
						)
					)
				else
					(HandsOff)
					(ego
						view: (if gunDrawn 7 else 1)
						setLoop: 0
						cel: 7
						posn: 20 98
						init:
						illegalBits: 0
						setPri: 10
						setMotion: MoveTo 50 134 self
						setCycle: 0
						ignoreActors: 1
					)
				)
			)
			(1
				(ego
					setLoop: -1
					setCel: -1
					setCycle: Walk
					setMotion: MoveTo 85 134 self
				)
			)
			(2
				(ego
					ignoreActors: 0
					illegalBits: -32768
					setPri: -1
					setMotion: MoveTo 700 134
				)
				(= local11 1)
				(HandsOn)
				(User prevDir: 3)
				(if (Btst 40)
					((= keith (Act new:))
						view: 20
						setLoop: 0
						cel: 7
						posn: 20 98
						init:
						illegalBits: 0
						setPri: 10
						setMotion: MoveTo 50 134 self
						setCycle: 0
					)
				)
			)
			(3
				(if local10
					(keith
						setLoop: -1
						setCel: -1
						setCycle: Walk
						setPri: -1
						illegalBits: -32768
						setMotion: Follow ego 30
					)
				else
					(keith
						setLoop: -1
						setCel: -1
						setCycle: Walk
						setMotion: MoveTo 135 135 self
					)
				)
			)
			(4
				(keith illegalBits: -28672 setMotion: Follow ego 40)
			)
			(5
				(HandsOff)
				(ego
					setStep: 4 4
					setPri: 8
					illegalBits: 0
					setLoop: 1
					setCel: 7
					setMotion: MoveTo 16 85 self
				)
			)
			(6
				(= perspective 0)
				(curRoom newRoom: 20)
				(cSound fade:)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'display/mugshot,painting,(shot<mug)') (agentScript changeState: 2))
					((Said 'look,read/sign') (localproc_000c 17 2))
					((Said 'look>')
						(cond 
							((Said '/flyer,ad,schedule,law') (localproc_000c 17 3))
							((Said '/escalator') (localproc_000c 17 4))
							((Said '/stair') (localproc_000c 17 5))
							((or (Said '<up') (Said '/ceiling,light')) (localproc_000c 17 6))
							((or (Said '<down') (Said '/floor')) (localproc_000c 17 7))
							((Said '/pane')
								(if (== (ego loop?) 2)
									(localproc_000c 17 8)
								else
									(localproc_000c 17 9)
								)
							)
							((Said '/rope') (localproc_000c 17 10))
							((Said '[<at,around][/!*,chamber,building]') (localproc_000c 17 11))
							((Said '/dude,person')
								(cond 
									((<= (scruffy distanceTo: ego) 50) (localproc_000c 17 12))
									((== local4 1) (localproc_000c 17 13))
									((== local4 0) (localproc_000c 17 14))
									((ego inRect: 143 117 185 124) (localproc_000c 17 13))
									(else (localproc_000c 17 15))
								)
							)
							((Said '/agency,auto,rental,counter') (localproc_000c 17 16))
							((Said '/agent')
								(cond 
									((== local4 1) (localproc_000c 17 17))
									((== local4 2) (localproc_000c 17 15))
									(else (localproc_000c 17 16))
								)
							)
							((Said '/broad,broad')
								(if (< (ego y?) 137)
									(localproc_000c 17 17)
								else
									(localproc_000c 17 14)
								)
							)
							((Said '/passenger,customer[<rental]')
								(if (< (ego y?) 137)
									(localproc_000c 17 13)
								else
									(localproc_000c 17 14)
								)
							)
							(
								(or
									(Said '/list[<customer,rental,auto]')
									(Said '/agreement[<rental]')
									(Said '/rental')
								)
								(if (ego inRect: 124 116 218 124)
									(agentScript changeState: 1)
								else
									(localproc_000c 17 14)
								)
							)
						)
					)
					((Said 'ask/auto') (localproc_000c 17 18))
					(
						(or
							(Said 'display,get,see,ask/list[<customer,rental,auto]')
							(Said '[display,get,see,ask]/list,agreement,rental')
							(Said 'display,get,see,ask/i/list,rental,agreement')
							(Said 'chat,ask/agent/customer,rental,list,auto')
						)
						(if (ego inRect: 124 116 218 124)
							(agentScript changeState: 1)
						else
							(localproc_000c 17 19)
						)
					)
					((Said 'chat>')
						(cond 
							(
							(or (Said '/agent[<rental]') (Said '/broad,broad'))
								(cond 
									((== local4 0) (localproc_000c 17 19))
									((and (not local2) (not local3)) (agentScript changeState: 0))
									(else (localproc_000c 17 20))
								)
							)
							((Said '/dude,men')
								(cond 
									(
									(or (ego inRect: 143 117 185 124) (== local4 1)) (localproc_000c 17 21))
									((== local4 2)
										(if (and (not local2) (not local3))
											(agentScript changeState: 0)
										else
											(localproc_000c 17 20)
										)
									)
									((and (< (ego y?) 134) (> (ego x?) 226)) (localproc_000c 17 22))
									((<= (scruffy distanceTo: ego) 50) (localproc_000c 17 23))
									(else (localproc_000c 17 19))
								)
							)
							((Said '/passenger,customer[<rental]')
								(if (ego inRect: 143 117 185 124)
									(localproc_000c 17 21)
								else
									(localproc_000c 17 19)
								)
							)
						)
					)
					((Said 'display,flash/badge')
						(if (ego has: 7)
							(if (ego inRect: 124 116 218 124) (= local2 1))
							(cond 
								((== local4 1) (localproc_000c 17 24))
								((== local4 2) (localproc_000c 17 25))
								((<= (scruffy distanceTo: ego) 50) (localproc_000c 17 26))
								(else (localproc_000c 17 27))
							)
						else
							(localproc_000c 17 28)
						)
					)
					(
					(Said 'arrest/agent,dude,broad,customer,passenger,broad') (localproc_000c 17 29))
					(
						(Said
							'frisk,arrest/agent,dude,broad,broad,customer,passenger'
						)
						(localproc_000c 17 30)
					)
					(
						(Said
							'kill,fire,beat/agent,dude,customer,passenger,broad,broad'
						)
						(localproc_000c 17 31)
					)
					((Said 'rent[/auto,bus,bicycle]') (localproc_000c 17 32))
					((Said 'affirmative') (localproc_000c 17 33))
					((Said 'n') (localproc_000c 17 34))
					((Said 'thank[/ya,dude,broad,agent]')
						(if (ego inRect: 124 116 218 124)
							(agentScript changeState: 3)
						else
							(localproc_000c 17 19)
						)
					)
					((Said '[*]/bains') (localproc_000c 17 35))
				)
			)
		)
	)
)

(instance scruffyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< howFast 30) (client setScript: 0) (return))
				(= cycles (Random 100 300))
			)
			(1
				(if (== prevRoomNum 16)
					(scruffy
						posn: 26 102
						setPri: 10
						cel: 3
						setCycle: 0
						setMotion: MoveTo 50 134 self
						setAvoider: (Avoid new:)
						ignoreActors: 1
					)
				else
					(scruffy
						posn: -15 168
						setCycle: Walk
						setAvoider: (Avoid new:)
					)
					(self changeState: 3)
				)
			)
			(2
				(scruffy
					setCycle: Walk
					setMotion: MoveTo 82 134 self
					ignoreActors: 0
				)
			)
			(3
				(scruffy
					setPri: -1
					setMotion: MoveTo 362 178 self
					ignoreActors: 0
				)
			)
			(4
				(scruffy stopUpd:)
				(= cycles (Random 100 300))
			)
			(5
				(scruffy setLoop: 2 setMotion: MoveTo -15 168 self)
			)
			(6
				(scruffy setLoop: 1)
				(self changeState: 3)
			)
		)
	)
)

(instance agentScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (Random 0 5)
					(0 (localproc_000c 17 36))
					(1 (localproc_000c 17 37))
					(2 (localproc_000c 17 38))
					(3 (localproc_000c 17 39))
					(4 (localproc_000c 17 40))
					(5 (localproc_000c 17 41))
				)
			)
			(1
				(= local3 1)
				(if local2
					(if (== local4 2)
						(if local7
							(localproc_000c 17 42)
						else
							(localproc_000c 17 43)
						)
						(= local7 1)
						(localproc_000c 17 44)
						(localproc_000c 17 45)
					else
						(if local8
							(localproc_000c 17 42)
						else
							(localproc_000c 17 43)
						)
						(= local8 1)
						(localproc_000c 17 46)
						(if (>= gamePhase 6)
							(agentScript changeState: 4)
						else
							(localproc_000c 17 45)
						)
					)
				else
					(localproc_000c 17 47)
				)
			)
			(2
				(cond 
					(local2
						(cond 
							((ego has: 12)
								(switch local4
									(1
										(if (>= gamePhase 6)
											(localproc_000c 17 48 82 112)
											(SolvePuzzle 1 83)
										else
											(localproc_000c 17 49 82 112)
										)
									)
									(2
										(localproc_000c 17 49 82 112)
									)
								)
							)
							((ego has: 23) (localproc_000c 17 49 82 123))
							(else (localproc_000c 17 50))
						)
					)
					((or (ego has: 12) (ego has: 23))
						(localproc_000c
							17
							51
							82
							(if (ego has: 12) 112 else 123)
						)
					)
					(else (localproc_000c 17 50))
				)
			)
			(3
				(if local2
					(localproc_000c 17 52)
				else
					(localproc_000c 17 53)
				)
			)
			(4
				(SolvePuzzle 3 84)
				(localproc_000c 17 54 25 8)
				(localproc_000c 17 55 25 8)
				(localproc_000c 17 56 25 8)
				(self cue:)
			)
			(5
				(if (!= (cSound state?) 3)
					(cSound number: 29 loop: -1 play:)
				)
			)
		)
	)
)
