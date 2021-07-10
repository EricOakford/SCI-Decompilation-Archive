;;; Sierra Script 1.0 - (do not remove this comment)
(script# 38)
(include sci.sh)
(use Main)
(use Intrface)
(use subMarine)
(use EgoDead)
(use SolvePuzzle)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	escapeHatchRm 0
)

(local
	local0
	local1
)
(procedure (localproc_000c param1 param2 &tmp [temp0 40])
	(= temp0 0)
	(if (> argc 1) (Format @temp0 38 0 param2))
	(return
		(if (GetInput @temp0 10 param1)
			(ReadNumber @temp0)
		else
			-1
		)
	)
)

(procedure (localproc_0047)
	(Print 38 1)
)

(procedure (localproc_0050)
	(= local1 -1)
	(if
		(==
			(= local1
				(localproc_000c {Enter distance to waypoint #1:})
			)
			-1
		)
		(localproc_0047)
		(return)
	else
		(subMarine dist1: local1)
	)
	(= local1 -1)
	(if
		(==
			(= local1
				(localproc_000c {Enter heading to waypoint #1:})
			)
			-1
		)
		(localproc_0047)
		(return)
	else
		(subMarine head1: local1)
	)
	(= local1 -1)
	(if
		(==
			(= local1
				(localproc_000c {Enter distance to waypoint #2:})
			)
			-1
		)
		(localproc_0047)
		(return)
	else
		(subMarine dist2: local1)
	)
	(= local1 -1)
	(if
		(==
			(= local1
				(localproc_000c {Enter heading to waypoint #2:})
			)
			-1
		)
		(localproc_0047)
		(return)
	else
		(subMarine head2: local1)
	)
)

(instance escapeHatchRm of Rm
	(properties
		picture 38
		north 50
		south 37
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(LoadMany 128 238 138 438 232 38)
		(Load rsSOUND 59)
		(ego
			illegalBits: 0
			init:
			setPri: 8
			posn: 137 187
			view: 238
			heading: 0
		)
		(sideDoor init: setPri: 9)
		(ladder init: setPri: 1)
		(topHatchDoor init:)
		(if (== (subMarine suitRoom?) 38)
			(self setFeatures: scubaGear)
		)
		(self setFeatures: waterButton setRegions: 314)
		(ego setScript: roomScript)
	)
	
	(method (dispose)
		(if (>= newRoomNum 50)
			(DisposeScript 806)
			(DisposeScript 817)
			(ego put: 5 9 10 11 12 13 14 15 16 17)
		)
		(ego ignoreActors: 0)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<around,at][/room,scene]')
				(cond 
					((not local0) (Print 38 2))
					((== (subMarine suitRoom?) 38) (Print 38 3))
					(else (Print 38 4))
				)
			)
		)
	)
)

(instance roomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setPri: 11)
				(= cycles 1)
			)
			(1
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 137 170 self
				)
			)
			(2
				(= local0 0)
				(User canInput: 1 canControl: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((not (ego has: 6)))
			((Said '[/diver,vehicle[<dive]]>')
				(cond 
					((Said 'examine,check')
						(Print 38 5)
						(if
							(or
								(& (subMarine roomFlags?) $0040)
								(& (subMarine roomFlags?) $0080)
							)
							(Print 38 6)
						else
							(Print 38 7)
						)
					)
					((Said 'program') (localproc_0050))
					((Said 'get[<!*]/*') (AlreadyTook))
					((Said 'return,replace,drop,(adjust[<away])') (self cue:) (event claimed: 1))
				)
			)
			((Said 'examine,check/vibration')
				(if
					(and
						(not (& (subMarine roomFlags?) $0040))
						(not (& (subMarine roomFlags?) $0080))
					)
					(Print 38 8)
				else
					(Print 38 9)
				)
			)
			((Said 'examine,check/shaft')
				(cond 
					(
						(and
							(not (& (subMarine roomFlags?) $0040))
							(not (& (subMarine roomFlags?) $0020))
						)
						(Print 38 10)
					)
					(
						(and
							(& (subMarine roomFlags?) $0040)
							(& (subMarine roomFlags?) $0020)
						)
						(Print 38 11)
						(if (not (& (subMarine roomFlags?) $0080))
							(Print 38 12)
						)
					)
					((not (& (subMarine roomFlags?) $0040)) (Print 38 13))
					((not (& (subMarine roomFlags?) $0020)) (Print 38 14))
				)
			)
			((Said 'look[<at]/shaft') (Print 38 15))
			((Said 'measure/shaft')
				(if (not (ego has: 13))
					(Print 38 16)
				else
					(Print 38 17)
					(SolvePuzzle subMarine 406 8192 5)
				)
			)
			((Said '/prop>')
				(cond 
					((Said 'look[<at]') (Print 38 18))
					((Said 'examine,check') (Print 38 19))
					((Said 'measure') (DontNeedTo))
					((Said 'fix,install') (DontNeedTo))
					((Said 'tighten') (DontNeedTo))
				)
			)
			(
				(or
					(Said 'install,(adjust<on)/washer')
					(Said 'washer<use')
				)
				(cond 
					((& (subMarine roomFlags?) $0020) (Print 38 20))
					((not (ego has: 10)) (Print 38 21))
					((== (subMarine invStatus4?) 1) (Print 38 22))
					((> (subMarine invStatus4?) 2) (Print 38 23))
					((& (subMarine roomFlags?) $0040) (Print 38 24))
					(else
						(Print 38 25)
						(subMarine roomFlags: (| (subMarine roomFlags?) $0020))
						(SolvePuzzle subMarine 407 2048 1)
						(ego put: 10)
					)
				)
			)
			((Said 'get,detach,(get<off)/washer')
				(cond 
					((!= ((inventory at: 10) owner?) curRoom) (event claimed: 0))
					((& (subMarine roomFlags?) $0040) (Print 38 26))
					(else
						(Print 38 27)
						(ego get: 10)
						(subMarine roomFlags: (& (subMarine roomFlags?) $ffdf))
						(ego put: 10 curRoom)
					)
				)
			)
			(
			(or (Said 'install,(adjust<on)/nut') (Said 'nut<use'))
				(cond 
					((& (subMarine roomFlags?) $0040) (Print 38 20))
					((not (ego has: 11)) (Print 38 28))
					((== (subMarine invStatus3?) 1) (Print 38 29))
					((> (subMarine invStatus3?) 2) (Print 38 30))
					(else
						(if (& (subMarine roomFlags?) $0020)
							(Print 38 31)
						else
							(Print 38 32)
						)
						(subMarine roomFlags: (| (subMarine roomFlags?) $0040))
						(ego put: 11 curRoom)
					)
				)
			)
			((Said 'tighten/nut')
				(cond 
					((not (& (subMarine roomFlags?) $0040)) (event claimed: 0))
					((& (subMarine roomFlags?) $0080) (Print 38 20))
					(
					(and (ego has: 16) (== (subMarine invStatus2?) 2))
						(Print 38 33)
						(subMarine roomFlags: (| (subMarine roomFlags?) $0080))
						(theGame changeScore: 3)
					)
					(
					(and (ego has: 16) (!= (subMarine invStatus2?) 2)) (Print 38 34))
					(else (Print 38 35))
				)
			)
			((Said 'get,detach,(get<off)/nut')
				(cond 
					((ego has: 11) (Print 38 36))
					((!= ((inventory at: 11) owner?) curRoom) (event claimed: 0))
					((not (& (subMarine roomFlags?) $0080))
						(Print 38 37)
						(subMarine roomFlags: (& (subMarine roomFlags?) $ffbf))
						(ego get: 11)
					)
					((& (subMarine roomFlags?) $0080)
						(cond 
							((not (ego has: 16)) (Print 38 38))
							((!= (subMarine invStatus2?) 2) (Print 38 34))
							(else
								(Print 38 37)
								(subMarine roomFlags: (& (subMarine roomFlags?) $ff7f))
								(subMarine roomFlags: (& (subMarine roomFlags?) $ffbf))
								(ego get: 11)
							)
						)
					)
				)
			)
			(
			(Said 'enter,program/heading,distance,coordinate') (localproc_0050))
			(
			(or (Said 'repair,fix/shaft') (Said 'use/wrench')) (Print 38 39))
		)
	)
)

(instance waterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(subMarine cue: 1)
				(ego ignoreActors: view: 138 setLoop: 0 setCel: 0)
				(= cycles 20)
			)
			(1
				(ego x: 187 y: 130 setCycle: End self)
			)
			(2
				(water init:)
				(water setLoop: 1 setCel: 0 setCycle: End self)
			)
			(3
				(Print 38 40 #at 5 6)
				(= cycles 1)
			)
			(4
				(water setLoop: 2 setCel: 0 setCycle: End self)
			)
			(5
				(Print 38 41 #at 2 102)
				(= cycles 1)
			)
			(6
				(ego setPri: 10 setLoop: 3 setCel: 0)
				(water setLoop: 4 setCel: 0)
				(= cycles 1)
			)
			(7 (ego setCycle: End self))
			(8
				(ego x: 188 y: 82 setLoop: 3 setCel: 0)
				(= cycles 4)
			)
			(9
				(Print 38 42 #at 150 100)
				(= cycles 1)
			)
			(10
				(topHatchDoor setCycle: End self)
			)
			(11
				(ego
					loop: 5
					cel: 0
					setStep: 3 6
					illegalBits: 0
					posn: 181 86
					setMotion: MoveTo 181 -12 self
				)
			)
			(12
				(ego setPri: -1 illegalBits: -32768 setStep: 3 4 put: 0 1)
				(HandsOn)
				(curRoom newRoom: 50)
			)
		)
	)
)

(instance deadWaterScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(and (== (gurgle prevSignal?) 10) (== state 2))
				(and (== (gurgle prevSignal?) 20) (== state 3))
				(== (gurgle prevSignal?) -1)
			)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 187 130 self
				)
			)
			(1
				(ego setCel: 0 setLoop: 3 setPri: 8)
				(= cycles 1)
			)
			(2
				(deathWater init: setPri: 7)
				(ego posn: 159 125)
				(deathWater setLoop: 1 setCel: 0 setCycle: End)
				(gurgle play:)
			)
			(3
				(deathWater
					setLoop: 2
					setCel: 0
					cycleSpeed: 1
					setCycle: End
				)
			)
			(4
				(ego
					view: 438
					setLoop: 3
					setCel: 0
					setMotion: MoveTo 196 116 self
				)
			)
			(5
				(ego setMotion: MoveTo 222 90)
			)
			(6 (EgoDead 926 0 0 38 43))
		)
	)
)

(instance openDoorFromInside of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 ignoreActors:)
				(= cycles 1)
			)
			(1
				(sideDoor setCycle: End self)
			)
			(2
				(ego setMotion: MoveTo 137 130 self)
			)
			(3
				(= local0 0)
				(ego
					setPri: 11
					view: 238
					x: 137
					y: 130
					setMotion: MoveTo 137 170 self
				)
			)
			(4
				(sideDoor setCycle: Beg self)
			)
			(5
				(User canInput: 1)
				(if register
					(self setScript: climbDownLadder)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance openDoorFromOutside of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 1))
			(1
				(sideDoor setCycle: End self)
			)
			(2
				(ego setPri: 11 setMotion: MoveTo 137 130 self)
			)
			(3
				(ego
					view: 232
					setPri: 8
					illegalBits: -32768
					ignoreActors: 0
				)
				(= cycles 1)
			)
			(4
				(ego setMotion: MoveTo 188 122 self)
			)
			(5
				(sideDoor setCycle: Beg self)
			)
			(6
				(= local0 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance climbDownLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 137 187 self)
			)
			(1
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance sideDoor of Prop
	(properties
		y 119
		x 148
		view 38
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/hatch,door>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 38 44))
					((Said 'open')
						(if (not local0)
							(curRoom setScript: openDoorFromOutside)
						else
							(curRoom setScript: openDoorFromInside)
						)
					)
					((Said 'close') (ItIs))
				)
			)
		)
	)
)

(instance topHatchDoor of Prop
	(properties
		y 23
		x 160
		view 38
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/hatch,door>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 38 45))
				)
			)
		)
	)
)

(instance ladder of Prop
	(properties
		y 172
		x 137
		view 38
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/ladder]>')
				(cond 
					((Said 'look[<at]') (Print 38 46))
					((Said 'climb')
						(if local0
							(curRoom setScript: openDoorFromInside 0 1)
						else
							(curRoom setScript: climbDownLadder)
						)
					)
				)
			)
		)
	)
)

(instance scubaGear of RFeature
	(properties
		y 118
		x 107
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/gear,scuba,coat,wetsuit,equipment]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(if (== (subMarine suitRoom?) 38)
							(Print 38 47)
						else
							(event claimed: 1)
							(CantSee)
						)
					)
					((GoToIfSaid self event self 10 0 38 48))
					((Said 'get,wear,(adjust<on)')
						(if (== (subMarine suitRoom?) 38)
							(Print 38 49)
							(theGame changeScore: 1)
							(curRoom setScript: waterScript)
						else
							(event claimed: 1)
							(CantSee)
						)
					)
				)
			)
		)
	)
)

(instance waterButton of RFeature
	(properties
		y 118
		x 108
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/button>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 38 50))
					((GoToIfSaid self event self 10 0 38 48))
					((Said 'press,push') (curRoom setScript: deadWaterScript))
					(else (CantDo))
				)
			)
		)
	)
)

(instance water of Prop
	(properties
		y 130
		x 187
		view 138
		cycleSpeed 2
	)
)

(instance deathWater of Prop
	(properties
		y 130
		x 187
		view 438
		cycleSpeed 2
	)
)

(instance gurgle of Sound
	(properties
		number 59
		priority 2
	)
)
