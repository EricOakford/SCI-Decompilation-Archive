;;; Sierra Script 1.0 - (do not remove this comment)
(script# 34)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use n315)
(use InitAllFeatures)
(use SolvePuzzle)
(use Approach)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Sight)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	machineRm 0
	machNoise 1
	dial1 2
	dial2 3
	dial3 4
	shaft 5
)

(local
	printRet
	local1
	local2
	local3 = [127 175 265]
	local6 = [134 136 120]
	local9 = [127 175 265]
	local12 = [140 142 124 48 88 254 93 92 88 74 139 287 101 98 97]
)
(procedure (CabinetClosed)
	(Print 34 0)
)

(instance machineRm of Room
	(properties
		picture 34
		east 41
		south 35
		west 36
	)
	
	(method (init)
		(LoadMany VIEW 34 232 434 534)
		(LoadMany SOUND 13 213 19 219 30 230 31 231 17 217)
		(ScriptID 315)
		(super init:)
		(addToPics
			add: lathe drill grinder
			eachElementDo: #init
			doit:
		)
		(InitAllFeatures)
		(self setRegions: 314 setFeatures: pump system)
		(tubeDoor init:)
		(cabDoor init:)
		(drillHead init:)
		(blade
			init:
			illegalBits: 0
			setLoop: 0
			setCel: 1
			setStep: 1 1
			moveSpeed: 1
		)
		(dial1 init:)
		(dial2 init:)
		(dial3 init:)
		(willie
			init:
			setCycle: Walk
			setAvoider: Avoider
			setScript: willieScript
		)
		(switch prevRoomNum
			(west
				(tubeDoor setCel: (tubeDoor lastCel:))
				(ego posn: 5 121)
			)
			(else 
				(ego posn: 297 98 loop: 1)
				(tubeDoor setCel: 0 setCycle: EndLoop tubeDoor)
			)
		)
		(ego init:)
	)
	
	(method (dispose)
		(DisposeScript 315)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,scene,equipment]')
				(Print 34 2)
				(Print 34 3)
			)
			((Said 'cycle/equipment')
				(Print 34 4)
			)
			((Said 'examine,look[<at]/cylinder')
				(if (not (ego has: iMetalCylinder))
					(Print 34 5)
				else
					((inventory at: iMetalCylinder) showSelf:)
				)
			)
			((Said 'measure/cylinder')
				(cond 
					((not (ego has: iMetalCylinder))
						(Print 34 5)
					)
					((not (ego has: iVernierCaliper))
						(Print 34 6)
					)
					((== (subMarine invStatus1?) 4) (Print 34 7))
					(else
						(Printf 34 8
							(switch (subMarine invStatus1?)
								(1 {3})
								(2 {4})
								(3 {6})
							)
						)
						(if (subMarine cylDiam?)
							(Printf 34 9
								(switch (subMarine cylDiam?)
									(1 {1"})
									(2 {1.5"})
									(3 {2"})
								)
							)
						)
						(if (subMarine holeSize?)
							(Printf 34 10
								(switch (subMarine holeSize?)
									(1 {1/32"})
									(2 {1/16"})
									(3 {1/8"})
									(4 {1/4"})
									(5 {1/2"})
									(6 {3/4"})
									(7 {1"})
								)
							)
						)
						(if (& (subMarine roomFlags?) $0004)
							(Print 34 11)
						else
							(Print 34 12)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom nRoom)
		(HandsOn)
		(ego illegalBits: cWHITE)
		(super newRoom: nRoom)
	)
)

(instance willieScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 7)
			)
			(1
				(if (== register 3)
					(= start 4)
					(self init:)
				else
					(client
						setMotion: MoveTo [local3 register] [local6 register] self
					)
				)
			)
			(2
				(client
					setMotion: MoveTo [local9 register] [local12 register] self
				)
				(++ register)
			)
			(3 (self init:))
			(4
				(client setMotion: MoveTo 300 95 self)
			)
			(5
				(tubeDoor setCel: (tubeDoor lastCel:) setCycle: BegLoop self)
			)
			(6
				(tubeDoor stopUpd:)
				(client
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo 325 84 self
				)
			)
			(7
				(tubeDoor setCycle: EndLoop self)
			)
			(8
				(tubeDoor stopUpd:)
				(client dispose:)
			)
		)
	)
)

(instance willie of Actor
	(properties
		y 150
		x 76
		view 534
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look,address/man,johnson'))
			((Said 'look[<at][/man,johnson]')
				(Print 34 13)
			)
			((Said 'address/man,johnson')
				(if (& (subMarine roomFlags?) $0008)
					(Print 34 14)
				else
					(Print 34 15)
					(Print 34 16)
					(subMarine roomFlags: (| (subMarine roomFlags?) $0008))
				)
			)
			((GoToIfSaid self event self 20 'get/key' 34 17))
			((Said 'get/key')
				(cond 
					((ego has: iSubKey)
						(Print 34 18)
					)
					((>= ((subMarine script?) state?) 15)
						(Print 34 19)
						(ego get: iSubKey)
					)
					(else
						(Print 34 20)
					)
				)
			)
		)
	)
)

(instance lathe of RPicView
	(properties
		y 92
		x 113
		view 34
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/lathe]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 34 21)
					)
					((GoToIfSaid self event self 10 0 34 17))
					((Said 'use')
						(cond 
							((== (subMarine cylDiam?) 1)
								(Print 34 22)
							)
							((& local1 $0001)
								(Print 34 23)
							)
							((ego has: iMetalCylinder)
								(Print 34 24)
								(shaft init:)
								(|= local1 $0001)
								(ego put: iMetalCylinder curRoom)
							)
							(else
								(Print 34 25)
							)
						)
					)
					((Said 'adjust')
						(cond 
							((& local1 $0002)
								(Print 34 26)
							)
							(
								(not
									(= printRet
										(MachinePrint 34 27
											#theItem {__1"__} 1
											#theItem { 1.5"_} 2
											#theItem {__2"__} 3
										)
									)
								)
								(Print 34 28)
							)
							(
								(and
									(< (subMarine cylDiam?) printRet)
									(subMarine cylDiam?)
								)
								(Print 34 29)
							)
							((== (subMarine cylDiam?) printRet) (Print 34 30))
							(else
								(= local1 (| local1 $0002))
								(switch printRet
									(1
										(Print 34 31)
										(SolvePuzzle subMarine 406 256 2)
									)
									(2 (Print 34 32))
									(3 (Print 34 33))
								)
							)
						)
					)
					((Said 'begin,(turn<on)')
						(cond 
							((and (& local1 $0002) (& local1 $0001))
								(blade setScript: (ScriptID 388 0))
								(subMarine cylDiam: printRet)
								(ego get: 12)
								(= local1 (& (= local1 (& local1 $fffd)) $fffe))
							)
							((and (& local1 $0002) (not (& local1 $0001)))
								(Print 34 24)
								(shaft init:)
								(blade setScript: (ScriptID 388 0))
								(subMarine cylDiam: printRet)
								(= local1 (& (= local1 (& local1 $fffd)) $fffe))
							)
							((and (not (& local1 $0002)) (& local1 $0001)) (Print 34 34))
							(
							(and (not (& local1 $0002)) (not (& local1 $0001))) (Print 34 35))
						)
					)
				)
			)
			(
				(or
					(Said '/cylinder<chuck,lathe')
					(Said 'adjust,secure<in/cylinder/lathe')
					(Said 'turn/cylinder')
				)
				(ego setScript: walkLatheScript)
			)
			((Said 'get,detach/cylinder')
				(if (!= local1 1)
					(event claimed: FALSE)
				else
					(ego setScript: getCylScript 0 0)
				)
			)
		)
	)
)

(instance walkLatheScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setAvoider: Avoider setMotion: Approach lathe 10 self)
			)
			(1
				(cond 
					((== (subMarine cylDiam?) 1)
						(Print 34 22)
					)
					((& local1 $0001)
						(Print 34 23)
					)
					((ego has: iMetalCylinder)
						(Print 34 24)
						(shaft init:)
						(|= local1 $0001)
						(ego put: iMetalCylinder curRoom)
					)
					(else
						(Print 34 25)
					)
				)
				(ego setAvoider: 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance drill of RPicView
	(properties
		y 94
		x 60
		view 34
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said '[/drill,press,hole]>')
					(Said 'drill,press[/cylinder]>')
				)
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 34 36)
					)
					((GoToIfSaid self event 79 97 '<use' 34 17))
					(
						(GoToIfSaid self event 79 97
							'drill,press,begin,(turn<on)'
							34 17
						)
					)
					((GoToIfSaid self event 79 97 0 34 17))
					((or (Said '<use') (Said '/hole,cylinder'))
						(cond 
							((& local2 $0001)
								(Print 34 37)
							)
							((not (ego has: iMetalCylinder))
								(Print 34 38)
							)
							((== (subMarine holeSize?) 7)
								(Print 34 39)
							)
							(else
								(Print 34 40)
								(|= local2 $0001)
								(ego put: iMetalCylinder curRoom)
							)
						)
					)
					((Said 'begin,(turn<on)')
						(cond 
							((and (& local2 $0002) (& local2 $0001))
								(drillHead setScript: (ScriptID 388 1))
								(if (== printRet 4)
									(SolvePuzzle subMarine #pointFlag1 $0800 1))
								(subMarine holeSize: printRet)
								(ego get: iMetalCylinder)
								(= local2 (& (= local2 (& local2 $fffd)) $fffe))
							)
							((and (& local2 $0002) (not (& local2 $0001)))
								(Print 34 40)
								(drillHead setScript: (ScriptID 388 1))
								(if (== printRet 4) (SolvePuzzle subMarine #pointFlag1 $0800 1))
								(subMarine holeSize: printRet)
								(= local2 (& (= local2 (& local2 $fffd)) $fffe))
							)
							((and (not (& local2 $0002)) (& local2 $0001))
								(Print 34 41)
							)
							((and (not (& local2 $0002)) (not (& local2 $0001)))
								(Print 34 42)
							)
						)
					)
				)
			)
			((Said '/bit,(size<bit)>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 34 43)
					)
					((GoToIfSaid self event 79 97 'get,choose,pick' 34 17))
					((Said 'get,choose,pick')
						(cond 
							(
								(not
									(= printRet
										(MachinePrint 34 44
											#theItem { 1/32"_} 1
											#theItem { 1/16"_} 2
											#theItem { 1/8"_} 3
											#theItem { 1/4"_} 4
											#theItem { 1/2"_} 5
											#theItem { 3/4"_} 6
											#theItem {__1"__} 7
										)
									)
								)
								(Print 34 45)
							)
							((> (subMarine holeSize?) printRet)
								(Print 34 46)
							)
							((== (subMarine holeSize?) printRet)
								(Print 34 47)
							)
							(else
								(Print 34 48)
								(|= local2 $0002)
								(if (== printRet 4)
									(SolvePuzzle subMarine #pointFlag1 $0400 2))
							)
						)
					)
				)
			)
			((Said 'get,detach/cylinder')
				(if (!= local2 1)
					(event claimed: FALSE)
				else
					(ego setScript: getCylScript 0 1)
				)
			)
		)
	)
)

(instance grinder of RPicView
	(properties
		y 96
		x 31
		view 34
		loop 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/grinder]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 34 49)
					)
					((GoToIfSaid self event self 10 'use,begin,(turn<on)' 34 17))
					((Said 'use,begin,(turn<on)')
						(cond 
							((not (ego has: iMetalCylinder))
								(Print 34 50)
							)
							((& (subMarine roomFlags?) $0004)
								(Print 34 51)
							)
							(else
								(Print 34 52)
								(drillHead setScript: (ScriptID 388 2))
							)
						)
					)
				)
			)
		)
	)
)

(instance getCylScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if register
					(ego setAvoider: Avoider setMotion: Approach drill 10 self)
				else
					(ego setAvoider: Avoider setMotion: Approach lathe 10 self)
				)
			)
			(1
				(Print 34 53)
				(if (not register)
					(shaft dispose:)
				)
				(&= local2 $fffe)
				(&= local1 $fffe)
				(ego setAvoider: 0 get: iMetalCylinder)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance pump of RFeature
	(properties
		y 136
		x 114
		nsTop 105
		nsLeft 91
		nsBottom 168
		nsRight 138
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/pump]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 34 54)
					)
					((Said 'turn<on,operate')
						(Print 34 55)
					)
					((Said 'examine')
						(Print 34 56)
					)
				)
			)
			((Said 'move/water')
				(Print 34 55)
			)
		)
	)
)

(instance system of RFeature
	(properties
		y 67
		x 218
		nsTop 48
		nsLeft 191
		nsBottom 86
		nsRight 245
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/system]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 34 57)
					)
					((Said 'turn<on,operate')
						(Print 34 58)
					)
					((Said 'examine')
						(Print 34 56)
					)
				)
			)
		)
	)
)

(instance doorSound of Sound
	(properties
		number 19
		priority 2
	)
)

(instance machNoise of Sound
	(properties
		priority 3
	)
)

(instance tubeDoor of Prop
	(properties
		y 85
		x 316
		view 34
		loop 5
		cel 6
		signal ignrAct
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== (ego onControl: origin) cBLUE)
				(not (self cycler?))
				cel
			)
			(HandsOff)
			(self setCycle: BegLoop self)
			(ego illegalBits: 0 setMotion: MoveTo 320 84)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 34 59)
					)
					((Said 'close')
						(Print 34 60)
					)
					((GoToIfSaid self event self 20 0 34 17))
					((Said 'open')
						(HandsOff)
						(ego illegalBits: 0 setMotion: MoveTo 320 84)
					)
				)
			)
		)
	)
	
	(method (setCycle)
		(super setCycle: &rest)
		(doorSound number: (SoundFX 13) play:)
	)
	
	(method (cue)
		(self stopUpd:)
		(doorSound number: (SoundFX 19) play:)
	)
)

(instance cabDoor of Prop
	(properties
		y 88
		x 271
		view 34
		loop 6
		signal (| ignrAct stopUpdOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/cabinet]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at,in]')
						(if (== (self cel?) 0)
							(Print 34 62)
						else
							(Print 34 63)
						)
					)
					((GoToIfSaid self event 256 95 0 34 17))
					((Said 'open')
						(if (!= (self cel?) 0)
							(Print 34 64)
						else
							(self setCycle: EndLoop self)
						)
						(ego heading: 90)
						((ego looper?) doit: ego (ego heading?))
					)
					((Said 'close')
						(if (== (self cel?) 0)
							(Print 34 65)
						else
							(self setCycle: BegLoop self)
						)
						(ego heading: 90)
						((ego looper?) doit: ego (ego heading?))
					)
				)
			)
			((Said '[/bin]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(if (== (self cel?) 0)
							(CabinetClosed)
						else
							(Print 34 66)
						)
					)
					((GoToIfSaid self event 256 95 0 34 17))
					((Said 'get[<!*]/*')
						(if (== (self cel?) 0)
							(CabinetClosed)
						else
							(Print 34 67)
						)
					)
				)
			)
			((Said '[/cylinder]>')
				(cond 
					((and (CantBeSeen self ego 180 50) (Said 'look[<at]'))
						(event claimed: FALSE)
					)
					((TurnIfSaid self event 'examine,look[<at]/*'))
					((Said 'examine,look[<at]')
						(if (not cel)
							(CabinetClosed)
						else
							(Print 34 68)
						)
					)
					(
						(and
							(or (& local1 $0001) (& local2 $0001))
							(Said 'get[<!*]/*')
						)
						(event claimed: 0)
					)
					(
					(GoToIfSaid self event 256 95 'get[<!*]/*' 34 17))
					((Said 'get[<!*]/*')
						(cond 
							((not cel) (CabinetClosed))
							(
								(= printRet
									(MachinePrint 34 69
										#theItem { 3"_} 1
										#theItem { 4"_} 2
										#theItem { 6"_} 3
									)
								)
								(if (ego has: iMetalCylinder)
									(Printf 34 70 (+ (* printRet 2) (== printRet 1)))
								else
									(Printf 34 71 (+ (* printRet 2) (== printRet 1)))
								)
								(subMarine cylDiam: 0)
								(subMarine holeSize: 0)
								(subMarine roomFlags: (& (subMarine roomFlags?) $fffb))
								(ego get: iMetalCylinder)
								(subMarine invStatus1: printRet)
								(if (== printRet 3)
									(SolvePuzzle subMarine #pointFlag1 $0040 1)
								)
							)
							(else (Print 34 72))
						)
					)
				)
			)
			((Said '[/bolt]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(if (not cel)
							(CabinetClosed)
						else
							(Print 34 73)
						)
					)
					((GoToIfSaid self event 256 95 0 34 17))
					((Said 'get[<!*]/*')
						(if (not cel)
							(CabinetClosed)
						else
							(Print 34 74)
						)
					)
				)
			)
			((Said '[/nut]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(if (not cel)
							(CabinetClosed)
						else
							(Print 34 75)
						)
					)
					((GoToIfSaid self event 256 95 'get[<!*]/*' 34 17))
					((Said 'get[<!*]/*')
						(cond 
							((not cel)
								(CabinetClosed)
							)
							(
								(= printRet
									(MachinePrint 34 69
										#theItem { 1/4"_} 1
										#theItem { 1/2"_} 2
										#theItem { 3/4"_} 3
										#theItem {__1"__} 4
									)
								)
								(if (ego has: iNut)
									(Printf 34 76
										(switch printRet
											(1 {1/4})
											(2 {1/2})
											(3 {3/4})
											(4 {1})
										)
									)
								else
									(Printf 34 77
										(switch printRet
											(1 {1/4})
											(2 {1/2})
											(3 {3/4})
											(4 {1})
										)
									)
								)
								(ego get: iNut)
								(subMarine invStatus3: printRet)
							)
							(else
								(Print 34 78)
							)
						)
					)
				)
			)
			((Said '[/washer]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(if (not cel)
							(CabinetClosed)
						else
							(Print 34 79)
						)
					)
					((GoToIfSaid self event 256 95 'get[<!*]/*' 34 17))
					((Said 'get[<!*]/*')
						(cond 
							((not cel) (CabinetClosed))
							(
								(= printRet
									(MachinePrint 34 69
										#theItem { 1/4"_} 1
										#theItem { 1/2"_} 2
										#theItem { 3/4"_} 3
										#theItem {__1"__} 4
									)
								)
								(if (ego has: iWasher)
									(Printf 34 80
										(switch printRet
											(1 {1/4})
											(2 {1/2})
											(3 {3/4})
											(4 {1})
										)
									)
								else
									(Printf 34 81
										(switch printRet
											(1 {1/4})
											(2 {1/2})
											(3 {3/4})
											(4 {1})
										)
									)
								)
								(ego get: iWasher)
								(subMarine invStatus4: printRet)
							)
							(else
								(Print 34 82)
							)
						)
					)
				)
			)
			((Said '[/pin<cotter]>')
				(cond 
					((Said 'measure')
						(cond 
							((not (ego has: iCotterPin))
								(Print 34 83)
							)
							((not (ego has: iVernierCaliper))
								(Print 34 6)
							)
							(else
								(Print 34 84)
								(SolvePuzzle subMarine #pointFlag1 $0080 1))
						)
					)
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(if (not cel)
							(CabinetClosed)
						else
							(Print 34 85)
						)
					)
					((GoToIfSaid self event 256 95 0 34 17))
					((Said 'get[<!*]/*')
						(if (not cel)
							(CabinetClosed)
						else
							(Print 34 86)
							(ego get: iCotterPin)
						)
					)
				)
			)
		)
	)
	
	(method (cue)
		(if (!= (self cel?) 0)
			(Print 34 61)
		)
		(self stopUpd:)
	)
)

(instance drillHead of Prop
	(properties
		y 57
		x 67
		view 34
		loop 3
		priority 6
		signal stopUpdOn
	)
)

(instance blade of Actor
	(properties
		y 93
		x 104
		z 17
		view 34
		cel 1
		signal (| ignrAct stopUpdOn)
	)
	
	(method (cue)
		(if (== x 115)
			(self setMotion: MoveTo 104 93 self)
		else
			(self setMotion: MoveTo 115 93 self)
		)
	)
)

(instance dial1 of Prop
	(properties
		y 93
		x 98
		z 17
		view 34
		loop 2
		cel 3
		signal stopUpdOn
	)
)

(instance dial2 of Prop
	(properties
		y 93
		x 98
		z 6
		view 34
		loop 2
		cel 3
		signal stopUpdOn
	)
)

(instance dial3 of Prop
	(properties
		y 93
		x 132
		z 18
		view 34
		loop 2
		cel 3
		signal stopUpdOn
	)
)

(instance shaft of Prop
	(properties
		y 68
		x 100
		view 34
		loop 1
	)
)
