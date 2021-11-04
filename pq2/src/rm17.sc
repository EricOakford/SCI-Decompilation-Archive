;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
(include system.sh)
(include game.sh)
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
	nearCounter
	local3
	whichAgent ;female: 1, male: 2
	scruffy
	local6
	seenList
	local8
	wrongEscalator
	local10
	local11
)
(procedure (LocPrint)
	(Print &rest #at -1 15)
)

(instance rm17 of Room
	(properties
		picture 17
		style HWIPE
	)
	
	(method (init)
		(super init:)
		(= perspective 70)
		(User canInput: 1 canControl: 1)
		(= gunFireState gunPROHIBITED)
		(= gunNotNeeded 1)
		(Load VIEW 1)
		(Load VIEW 20)
		(Load VIEW 78)
		(Load VIEW 77)
		((= scruffy (Actor new:))
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
			setCycle: Forward
			cycleSpeed: 1
			ignoreActors:
			init:
		)
		((= escalator2 (Prop new:))
			view: 78
			loop: 4
			posn: 54 139
			setPri: 9
			setCycle: Reverse
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
			((>= (ego x?) 320)
				(= perspective 0)
				(curRoom newRoom: 16)
			)
			(
				(and
					(< (ego x?) 31)
					(> (ego y?) 150)
				)
				(LocPrint 17 0)
				(ego setMotion: MoveTo 150 (ego y?))
			)
			((> (ego y?) 180)
				(LocPrint 17 0)
				(ego setMotion: MoveTo (ego x?) 150)
			)
			(
				(and
					local11
					(not wrongEscalator)
					(== (ego onControl: 1) cLMAGENTA)
				)
				(LocPrint 17 1)
				(= wrongEscalator 1)
				(ego cel: 6)
			)
			(
				(and
					wrongEscalator
					(!= (ego onControl: 1) cLMAGENTA)
				)
				(= wrongEscalator 0)
			)
			(
				(and
					(== (ego onControl: 1) cYELLOW)
					local11
				)
				(= local10 1)
				(self changeState: 5)
			)
		)
		(cond 
			((ego inRect: 134 116 185 124)
				(if (!= whichAgent 1)
					(= whichAgent 1)
				)
			)
			((ego inRect: 185 116 226 124)
				(if (!= whichAgent 2)
					(= whichAgent 2)
				)
			)
			(else
				(= whichAgent 0)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(StatusLine enable:)
				(if (== prevRoomNum 16)
					(User prevDir: 7)
					(ego
						view: (if gunDrawn 7 else 1)
						posn: 318 145
						init:
						setMotion: MoveTo 50 145
					)
					(= local11 1)
					(if (Btst fKeithFollows)
						((= keith (Actor new:))
							view: 20
							loop: 0
							cel: 7
							posn: (+ (ego x?) 42) (ego y?)
							setAvoider: (Avoider new:)
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
				(if (Btst fKeithFollows)
					((= keith (Actor new:))
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
						illegalBits: cWHITE ;-32768
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
				(keith
					illegalBits: cWHITE ;also cWhite I guess? was -28672
					setMotion: Follow ego 40
				)
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
			(saidEvent
				(cond 
					((Said 'display/mugshot,painting,(shot<mug)')
						(agentScript changeState: 2)
					)
					((Said 'look,read/sign')
						(LocPrint 17 2)
					)
					((Said 'look>')
						(cond 
							((Said '/flyer,ad,schedule,law')
								(LocPrint 17 3)
							)
							((Said '/escalator')
								(LocPrint 17 4)
							)
							((Said '/stair')
								(LocPrint 17 5)
							)
							(
								(or
									(Said '<up')
									(Said '/ceiling,light')
								)
								(LocPrint 17 6)
							)
							(
								(or
									(Said '<down')
									(Said '/floor')
								)
								(LocPrint 17 7)
							)
							((Said '/pane')
								(if (== (ego loop?) 2)
									(LocPrint 17 8)
								else
									(LocPrint 17 9)
								)
							)
							((Said '/rope')
								(LocPrint 17 10)
							)
							((Said '[<at,around][/!*,chamber,building]')
								(LocPrint 17 11)
							)
							((Said '/dude,person')
								(cond 
									((<= (scruffy distanceTo: ego) 50)
										(LocPrint 17 12)
									)
									((== whichAgent 1)
										(LocPrint 17 13)
									)
									((== whichAgent 0)
										(LocPrint 17 14)
									)
									((ego inRect: 143 117 185 124)
										(LocPrint 17 13)
									)
									(else
										(LocPrint 17 15)
									)
								)
							)
							((Said '/agency,auto,rental,counter')
								(LocPrint 17 16)
							)
							((Said '/agent')
								(cond 
									((== whichAgent 1)
										(LocPrint 17 17)
									)
									((== whichAgent 2)
										(LocPrint 17 15)
									)
									(else
										(LocPrint 17 16)
									)
								)
							)
							((Said '/broad,broad')
								(if (< (ego y?) 137)
									(LocPrint 17 17)
								else
									(LocPrint 17 14)
								)
							)
							((Said '/passenger,customer[<rental]')
								(if (< (ego y?) 137)
									(LocPrint 17 13)
								else
									(LocPrint 17 14)
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
									(LocPrint 17 14)
								)
							)
						)
					)
					((Said 'ask/auto')
						(LocPrint 17 18)
					)
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
							(LocPrint 17 19)
						)
					)
					((Said 'chat>')
						(cond 
							(
								(or
									(Said '/agent[<rental]')
									(Said '/broad,broad')
								)
								(cond 
									((== whichAgent 0)
										(LocPrint 17 19)
									)
									(
										(and
											(not nearCounter)
											(not local3)
										)
										(agentScript changeState: 0)
									)
									(else
										(LocPrint 17 20)
									)
								)
							)
							((Said '/dude,men')
								(cond 
									(
										(or
											(ego inRect: 143 117 185 124)
											(== whichAgent 1)
										)
										(LocPrint 17 21)
									)
									((== whichAgent 2)
										(if
											(and
												(not nearCounter)
												(not local3)
											)
											(agentScript changeState: 0)
										else
											(LocPrint 17 20)
										)
									)
									(
										(and
											(< (ego y?) 134)
											(> (ego x?) 226)
										)
										(LocPrint 17 22)
									)
									((<= (scruffy distanceTo: ego) 50)
										(LocPrint 17 23)
									)
									(else
										(LocPrint 17 19)
									)
								)
							)
							((Said '/passenger,customer[<rental]')
								(if (ego inRect: 143 117 185 124)
									(LocPrint 17 21)
								else
									(LocPrint 17 19)
								)
							)
						)
					)
					((Said 'display,flash/badge')
						(if (ego has: iWallet)
							(if (ego inRect: 124 116 218 124)
								(= nearCounter 1)
							)
							(cond 
								((== whichAgent 1)
									(LocPrint 17 24)
								)
								((== whichAgent 2)
									(LocPrint 17 25)
								)
								((<= (scruffy distanceTo: ego) 50)
									(LocPrint 17 26)
								)
								(else
									(LocPrint 17 27)
								)
							)
						else
							(LocPrint 17 28)
						)
					)
					(
					(Said 'arrest/agent,dude,broad,customer,passenger,broad')
						(LocPrint 17 29)
					)
					(
						(Said 'frisk,arrest/agent,dude,broad,broad,customer,passenger')
						(LocPrint 17 30)
					)
					((Said 'kill,fire,beat/agent,dude,customer,passenger,broad,broad')
						(LocPrint 17 31)
					)
					((Said 'rent[/auto,bus,bicycle]')
						(LocPrint 17 32)
					)
					((Said 'affirmative')
						(LocPrint 17 33)
					)
					((Said 'no')
						(LocPrint 17 34)
					)
					((Said 'thank[/ya,dude,broad,agent]')
						(if (ego inRect: 124 116 218 124)
							(agentScript changeState: 3)
						else
							(LocPrint 17 19)
						)
					)
					((Said '[*]/bains')
						(LocPrint 17 35)
					)
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
				(if (< howFast 30)
					(client setScript: 0)
					(return)
				)
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
						setAvoider: (Avoider new:)
						ignoreActors: 1
					)
				else
					(scruffy
						posn: -15 168
						setCycle: Walk
						setAvoider: (Avoider new:)
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
					(0
						(LocPrint 17 36)
					)
					(1
						(LocPrint 17 37)
					)
					(2
						(LocPrint 17 38)
					)
					(3
						(LocPrint 17 39)
					)
					(4
						(LocPrint 17 40)
					)
					(5
						(LocPrint 17 41)
					)
				)
			)
			(1
				(= local3 1)
				(if nearCounter
					(if (== whichAgent 2)
						(if seenList
							(LocPrint 17 42)
						else
							(LocPrint 17 43)
						)
						(= seenList 1)
						(LocPrint 17 44)
						(LocPrint 17 45)
					else
						(if local8
							(LocPrint 17 42)
						else
							(LocPrint 17 43)
						)
						(= local8 1)
						(LocPrint 17 46)
						(if (>= gamePhase 6)
							(agentScript changeState: 4)
						else
							(LocPrint 17 45)
						)
					)
				else
					(LocPrint 17 47)
				)
			)
			(2
				(cond 
					(nearCounter
						(cond 
							((ego has: iNewMugShot)
								(switch whichAgent
									(1
										(if (>= gamePhase 6)
											(LocPrint 17 48 82 112)
											(SolvePuzzle 1 fAgentRecognizedBains)
										else
											(LocPrint 17 49 82 112)
										)
									)
									(2
										(LocPrint 17 49 82 112)
									)
								)
							)
							((ego has: iOldMugShot)
								(LocPrint 17 49 82 123)
							)
							(else
								(LocPrint 17 50)
							)
						)
					)
					(
						(or
							(ego has: iNewMugShot)
							(ego has: iOldMugShot)
						)
						(LocPrint 17 51 82
							(if (ego has: iNewMugShot)
								112
							else
								123
							)
						)
					)
					(else
						(LocPrint 17 50)
					)
				)
			)
			(3
				(if nearCounter
					(LocPrint 17 52)
				else
					(LocPrint 17 53)
				)
			)
			(4
				(SolvePuzzle 3 fReadCarRentals)
				(LocPrint 17 54 25 8)
				(LocPrint 17 55 25 8)
				(LocPrint 17 56 25 8)
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
