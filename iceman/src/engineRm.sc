;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use n315)
(use InitAllFeatures)
(use SolvePuzzle)
(use Approach)
(use GoToSaid)
(use RFeature)
(use Sight)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	engineRm 0
)

(local
	printRet
	local1
	local2
	[local3 3]
)
(procedure (localproc_000c param1 param2 &tmp [temp0 40])
	(= temp0 0)
	(if (> argc 1) (Format @temp0 37 0 param2))
	(return
		(if (GetInput @temp0 10 param1)
			(ReadNumber @temp0)
		else
			-1
		)
	)
)

(procedure (AbortInput)
	(Print 37 1)
)

(procedure (ProgramDiver)
	(= local2 -1)
	(if
		(==
			(= local2
				(localproc_000c {Enter distance to waypoint #1:})
			)
			-1
		)
		(AbortInput)
		(return)
	else
		(subMarine dist1: local2)
	)
	(= local2 -1)
	(if
		(==
			(= local2
				(localproc_000c {Enter heading to waypoint #1:})
			)
			-1
		)
		(AbortInput)
		(return)
	else
		(subMarine head1: local2)
	)
	(= local2 -1)
	(if
		(==
			(= local2
				(localproc_000c {Enter distance to waypoint #2:})
			)
			-1
		)
		(AbortInput)
		(return)
	else
		(subMarine dist2: local2)
	)
	(= local2 -1)
	(if
		(==
			(= local2
				(localproc_000c {Enter heading to waypoint #2:})
			)
			-1
		)
		(AbortInput)
		(return)
	else
		(subMarine head2: local2)
	)
)

(instance engineRm of Room
	(properties
		picture 37
		north 38
		east 36
	)
	
	(method (init)
		(Load VIEW 37)
		(Load VIEW 232)
		(Load SOUND 3)
		(super init:)
		(globalSound
			number: 3
			owner: theGame
			priority: 1
			loop: -1
			play:
		)
		(driveShaft init:)
		(gauge init:)
		(diveUnitDoor loop: (if (ego has: 6) 7 else 3) init:)
		(drawer init:)
		(drawerInset init:)
		(ego init:)
		(if (< numColors 16)
			(addToPics add: buttonPV)
		)
		(addToPics add: doorPV eachElementDo: #init doit:)
		(InitAllFeatures)
		(switch prevRoomNum
			(north
				(ego view: 232 posn: 30 67 setScript: downLadderScript)
				(if (ego has: iDiver)
					(diveUnitDoor setScript: hasDiverScript)
				)
			)
			(else
				(ego posn: 310 110)
			)
		)
		(self
			setRegions: 314
			setFeatures:
				ladderF
				backLadderF
				transD1
				transD2
				turbine
				generator
				engine
				pump
				whiteTank
				greyTank
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look>')
				(cond 
					((Said '[<at,around][/room,scene]')
						(Print 37 2)
						(Print 37 3)
						(Print 37 4)
					)
					((Said '/pipe<green')
						(Print 37 5)
					)
					((Said '/pipe<red')
						(Print 37 6)
					)
					((Said '/pipe<blue')
						(Print 37 7)
					)
					((Said '/pipe<purple')
						(Print 37 8)
					)
					((Said '/pipe[<!*]')
						(Print 37 9)
					)
					((Said '/pipe<*')
						(Print 37 10)
					)
				)
			)
			((Said 'cycle/equipment')
				(Print 37 11)
			)
			((Said 'cycle/diver')
				(DontHave)
			)
		)
	)
)

(instance driveShaft of Prop
	(properties
		y 121
		x 62
		view 37
		loop 1
		priority 14
		signal fixPriOn
	)
	
	(method (init)
		(super init:)
		(if (> howFast slow)
			(self setCycle: Forward)
		else
			(self stopUpd:)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shaft<drive]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 12)
					)
					((Said 'examine')
						(Print 37 13)
					)
				)
			)
		)
	)
)

(instance gauge of Prop
	(properties
		y 111
		x 172
		view 37
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init:)
		(if (> howFast slow)
			(self setCycle: Forward)
		else
			(self stopUpd:)
		)
	)
)

(instance doorPV of RPicView
	(properties
		y 63
		x 274
		view 37
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 14)
					)
					((Said 'open,close')
						(CantDo)
					)
				)
			)
		)
	)
)

(instance buttonPV of PicView
	(properties
		y 79
		x 69
		view 37
		loop 2
		cel 1
		priority 4
	)
)

(instance diveUnitDoor of Prop
	(properties
		y 93
		x 90
		z 15
		view 37
		priority 4
		signal (| ignrAct fixPriOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door,bulkhead,wall,compartment,cabinet]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(cond 
							((not cel)
								(Print 37 15)
							)
							((not (ego has: iDiver))
								(Print 37 16)
							)
							(else
								(Print 37 17)
							)
						)
						(Print 37 18)
					)
					((GoToIfSaid self event self 5 0 37 19))
					((Said 'open')
						(if cel
							(ItIs)
						else
							(self setCycle: EndLoop)
						)
					)
					((Said 'close')
						(if cel
							(self setCycle: BegLoop)
						else
							(ItIs)
						)
					)
					((Said 'look[<in]')
						(cond 
							((not cel)
								(Print 37 20)
							)
							((ego has: iDiver)
								(Print 37 21)
							)
							(else
								(Print 37 22)
							)
						)
					)
				)
			)
			((Said '[/button]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 23)
					)
					((GoToIfSaid self event self 3 'push,press/*' 37 19))
					((Said 'push,press')
						(if cel
							(self setCycle: BegLoop)
						else
							(self setCycle: EndLoop)
						)
					)
				)
			)
			((Said '/diver,vehicle[<dive]>')
				(cond 
					((ego has: iDiver)
						(if (Said 'cycle,fix,repair')
							(Print 37 24)
						)
					)
					((TurnIfSaid self event 'get[<!*]/*'))
					((GoToIfSaid self event self 5 'get[<!*]/*' 37 19))
					((Said 'get[<!*]/*')
						(if (not cel)
							(Print 37 25)
						else
							(ego get: iDiver)
							(self setLoop: 7 setScript: hasDiverScript)
						)
					)
				)
			)
		)
	)
)

(instance hasDiverScript of Script
	
	(method (doit)
		(super doit:)
		(if (and (== (ego onControl: origin) cBLUE) (== state 0))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(Print 37 26)
				(ego
					setAvoider: Avoider
					setMotion: Approach diveUnitDoor 5 self
				)
			)
			(2
				(if (not (diveUnitDoor cel?))
					(diveUnitDoor setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(3
				(diveUnitDoor setLoop: 3)
				(= cycles 15)
			)
			(4
				(diveUnitDoor setCycle: BegLoop self)
			)
			(5
				(ego put: 6 setAvoider: 0)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/diver,vehicle[<dive]]>')
				(cond 
					((Said 'examine,check')
						(Print 37 27)
						(if
							(or
								(& (subMarine roomFlags?) $0040)
								(& (subMarine roomFlags?) $0080)
							)
							(Print 37 28)
						else
							(Print 37 29)
						)
					)
					((Said 'program')
						(ProgramDiver)
					)
					((Said 'get[<!*]/*')
						(AlreadyTook)
					)
					((Said 'return,replace,drop,(adjust[<away])')
						(self cue:)
						(event claimed: TRUE)
					)
				)
			)
			((Said 'examine,check/vibration')
				(if
					(and
						(not (& (subMarine roomFlags?) $0040))
						(not (& (subMarine roomFlags?) $0080))
					)
					(Print 37 30)
				else
					(Print 37 31)
				)
			)
			((Said 'examine,check/shaft')
				(cond 
					(
						(and
							(not (& (subMarine roomFlags?) $0040))
							(not (& (subMarine roomFlags?) $0020))
						)
						(Print 37 32)
					)
					(
						(and
							(& (subMarine roomFlags?) $0040)
							(& (subMarine roomFlags?) $0020)
						)
						(Print 37 33)
						(if (not (& (subMarine roomFlags?) $0080))
							(Print 37 34)
						)
					)
					((not (& (subMarine roomFlags?) $0040))
						(Print 37 35)
					)
					((not (& (subMarine roomFlags?) $0020))
						(Print 37 36)
					)
				)
			)
			((Said 'look[<at]/shaft')
				(Print 37 37)
			)
			((Said 'measure/shaft')
				(if (not (ego has: iVernierCaliper))
					(Print 37 38)
				else
					(Print 37 39)
					(SolvePuzzle subMarine 406 $2000 5)
				)
			)
			((Said '/prop>')
				(cond 
					((Said 'look[<at]')
						(Print 37 40)
					)
					((Said 'examine,check')
						(Print 37 41)
					)
					((Said 'measure')
						(DontNeedTo)
					)
					((Said 'fix,install')
						(DontNeedTo)
					)
					((Said 'tighten')
						(DontNeedTo)
					)
				)
			)
			(
				(or
					(Said 'install,(adjust<on)/washer')
					(Said 'washer<use')
				)
				(cond 
					((& (subMarine roomFlags?) $0020)
						(Print 37 42)
					)
					((not (ego has: iWasher))
						(Print 37 43)
					)
					((== (subMarine invStatus4?) 1)
						(Print 37 44)
					)
					((> (subMarine invStatus4?) 2)
						(Print 37 45)
					)
					((& (subMarine roomFlags?) $0040)
						(Print 37 46)
					)
					(else
						(Print 37 47)
						(subMarine roomFlags: (| (subMarine roomFlags?) $0020))
						(SolvePuzzle subMarine #pointFlag2 $0800 1)
						(ego put: 10)
					)
				)
			)
			((Said 'get,detach,(get<off)/washer')
				(cond 
					((!= ((inventory at: iWasher) owner?) curRoom)
						(event claimed: FALSE)
					)
					((& (subMarine roomFlags?) $0040)
						(Print 37 48)
					)
					(else
						(Print 37 49)
						(ego get: iWasher)
						(subMarine roomFlags: (& (subMarine roomFlags?) $ffdf))
						(ego put: iWasher curRoom)
					)
				)
			)
			(
			(or (Said 'install,(adjust<on)/nut') (Said 'nut<use'))
				(cond 
					((& (subMarine roomFlags?) $0040)
						(Print 37 42)
					)
					((not (ego has: iNut))
						(Print 37 50)
					)
					((== (subMarine invStatus3?) 1)
						(Print 37 51)
					)
					((> (subMarine invStatus3?) 2)
						(Print 37 52)
					)
					(else
						(if (& (subMarine roomFlags?) $0020)
							(Print 37 53)
						else
							(Print 37 54)
						)
						(subMarine roomFlags: (| (subMarine roomFlags?) $0040))
						(ego put: 11 curRoom)
					)
				)
			)
			((Said 'tighten/nut')
				(cond 
					((not (& (subMarine roomFlags?) $0040))
						(event claimed: FALSE)
					)
					((& (subMarine roomFlags?) $0080)
						(Print 37 42)
					)
					(
					(and (ego has: iOpenEndWrench) (== (subMarine invStatus2?) 2))
						(Print 37 55)
						(subMarine roomFlags: (| (subMarine roomFlags?) $0080))
						(SolvePuzzle subMarine #pointFlag2 $1000 3)
					)
					((and (ego has: iOpenEndWrench) (!= (subMarine invStatus2?) 2))
						(Print 37 56)
					)
					(else
						(Print 37 57)
					)
				)
			)
			((Said 'get,detach,(get<off)/nut')
				(cond 
					((ego has: iNut)
						(Print 37 58)
					)
					((!= ((inventory at: iNut) owner?) curRoom)
						(event claimed: FALSE)
					)
					((not (& (subMarine roomFlags?) $0080))
						(Print 37 59)
						(subMarine roomFlags: (& (subMarine roomFlags?) $ffbf))
						(ego get: iNut)
					)
					((& (subMarine roomFlags?) $0080)
						(cond 
							((not (ego has: iOpenEndWrench))
								(Print 37 60)
							)
							((!= (subMarine invStatus2?) 2)
								(Print 37 56)
							)
							(else
								(Print 37 59)
								(subMarine roomFlags: (& (subMarine roomFlags?) $ff7f))
								(subMarine roomFlags: (& (subMarine roomFlags?) $ffbf))
								(ego get: iNut)
							)
						)
					)
				)
			)
			((Said 'enter,program/heading,distance,coordinate')
				(ProgramDiver)
			)
			((or (Said 'repair,fix/shaft') (Said 'use/wrench'))
				(Print 37 24)
			)
		)
	)
)

(instance drawer of Prop
	(properties
		y 88
		x 214
		view 37
		loop 4
		priority 7
		signal (| fixPriOn stopUpdOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/drawer]>')
				(cond 
					((TurnIfSaid self event 'look[<at,in]/*'))
					((Said 'look[<at,in]')
						(Print 37 61)
						(if cel
							(drawerInset show:)
						)
					)
					((GoToIfSaid self event self 20 0 37 19))
					((Said 'open')
						(if cel
							(ItIs)
						else
							(self setCycle: EndLoop self)
							(ego heading: 0)
							((ego looper?) doit: ego (ego heading?))
						)
					)
					((Said 'close')
						(if cel
							(self setCycle: BegLoop self)
						else
							(ItIs)
						)
					)
				)
			)
			((Said '/hammer>')
				(cond 
					((and (ego has: iHammer) (Said 'look[<at]'))
						(event claimed: FALSE)
					)
					((TurnIfSaid self event 'look[<at]'))
					((Said 'look[<at]')
						(if (not cel)
							(Print 37 62)
						else
							(Print 37 63)
						)
					)
					((GoToIfSaid self event self 20 'get[<!*]/*,adjust' 37 19))
					((Said 'get[<!*]/*')
						(cond 
							((not cel)
								(Print 37 62)
							)
							((ego has: iHammer)
								(Print 37 64)
							)
							(else
								(ego get: iHammer)
								(drawerInset show: forceUpd:)
							)
						)
					)
					((Said 'adjust')
						(cond 
							((not (ego has: iHammer))
								(Print 37 65)
							)
							((not cel)
								(Print 37 62)
							)
							(else
								(ego put: iHammer)
								(drawerInset show: forceUpd:)
							)
						)
					)
				)
			)
			((Said '/wrench>')
				(cond 
					((and (ego has: iOpenEndWrench) (Said 'look[<at]'))
						(event claimed: FALSE)
					)
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(if (not cel)
							(Print 37 62)
						else
							(Print 37 66)
						)
					)
					((GoToIfSaid self event self 20 'get[<!*]/*,adjust' 37 19))
					((Said 'get[<!*]/*')
						(cond 
							((not cel)
								(Print 37 62)
							)
							(
								(= printRet
									(MachinePrint 37 67
										#theItem { 1/4"_} 1
										#theItem { 1/2"_} 2
										#theItem { 3/4"_} 3
										#theItem {__1"__} 4
									)
								)
								(if (ego has: iOpenEndWrench)
									(Printf 37 68
										(switch printRet
											(1 {1/4})
											(2 {1/2})
											(3 {3/4})
											(4 {1})
										)
									)
								else
									(Printf 37 69
										(switch printRet
											(1 {1/4})
											(2 {1/2})
											(3 {3/4})
											(4 {1})
										)
									)
								)
								(ego get: iOpenEndWrench)
								(subMarine invStatus2: printRet)
								(drawerInset show: forceUpd:)
								(if (== printRet 2)
									(SolvePuzzle subMarine #pointFlag1 $4000 2)
								)
							)
							(else
								(Print 37 70)
							)
						)
					)
					((Said 'adjust')
						(cond 
							((not (ego has: iOpenEndWrench))
								(Print 37 71)
							)
							((not cel)
								(Print 37 62)
							)
							(else (ego put: iOpenEndWrench)
								(drawerInset show: forceUpd:)
							)
						)
					)
				)
			)
			((Said 'look[<at]/tool')
				(if (not cel)
					(Print 37 62)
				else
					(Printf 37 72
						(if (ego has: iHammer) {} else { a hammer and})
					)
				)
			)
		)
	)
	
	(method (cue)
		(if cel
			(drawerInset show:)
		else
			(drawerInset hide:)
		)
		(self stopUpd:)
		(if cel
			(Print 37 73)
		)
	)
)

(instance drawerInset of View
	(properties
		y 44
		x 238
		heading 180
		view 37
		loop 6
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 hide:)
	)
	
	(method (doit)
		(super doit:)
		(if (CantBeSeen self ego 180)
			(self hide:)
		)
	)
	
	(method (hide)
		(super hide:)
		(|= signal staticView)
	)
	
	(method (show)
		(self
			cel:
				(cond 
					((ego has: iOpenEndWrench) (if (ego has: iHammer) 3 else 2))
					((ego has: iHammer) 1)
					(else 0)
				)
		)
		(super show:)
		(&= signal (~ staticView))
	)
)

(instance ladderF of Feature
	(properties
		y 95
		x 30
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/ladder]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 74)
					)
					(
					(GoToIfSaid self event self 20 'climb[<up]' 37 19))
					((Said 'climb[<up]')
						(ego setScript: climbLadderScript)
					)
					((Said 'climb<down')
						(Print 37 75)
					)
				)
			)
		)
	)
)

(instance backLadderF of Feature
	(properties
		y 78
		x 260
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/ladder]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 76)
					)
				)
			)
		)
	)
)

(instance climbLadderScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or (!= (ego x?) 31) (!= (ego y?) 101))
					(ego setMotion: MoveTo 31 101 self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				(client setMotion: MoveTo 30 100 self)
			)
			(2
				(client
					ignoreControl: cWHITE
					setPri: 5
					setMotion: MoveTo 30 64 self
				)
			)
			(3
				(client observeControl: cWHITE setPri: -1)
				(HandsOn)
				(curRoom newRoom: 38)
			)
		)
	)
)

(instance downLadderScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client
					ignoreControl: cWHITE
					posn: 30 67
					setPri: 5
					setMotion: MoveTo 30 100 self
					setLoop: 3
				)
			)
			(1
				(client observeControl: cWHITE setPri: -1 setLoop: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance transD1 of RFeature
	(properties
		y 120
		x 88
		nsTop 103
		nsLeft 78
		nsBottom 138
		nsRight 99
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/transducer]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 77)
					)
					((Said 'examine')
						(Print 37 78)
					)
				)
			)
		)
	)
)

(instance transD2 of RFeature
	(properties
		y 115
		x 46
		nsTop 103
		nsLeft 33
		nsBottom 128
		nsRight 60
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/transducer]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 77)
					)
					((Said 'examine')
						(Print 37 78)
					)
				)
			)
		)
	)
)

(instance turbine of RFeature
	(properties
		y 68
		x 161
		nsTop 58
		nsLeft 150
		nsBottom 79
		nsRight 172
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/turbine]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 79)
					)
					((Said 'examine')
						(Print 37 80)
					)
				)
			)
		)
	)
)

(instance generator of RFeature
	(properties
		y 112
		x 124
		nsTop 93
		nsLeft 112
		nsBottom 131
		nsRight 127
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/generator]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 81)
					)
					((Said 'examine')
						(Print 37 82)
					)
				)
			)
		)
	)
)

(instance engine of RFeature
	(properties
		y 110
		x 170
		nsTop 80
		nsLeft 127
		nsBottom 140
		nsRight 213
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/engine]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 83)
					)
					((Said 'examine')
						(Print 37 78)
					)
				)
			)
		)
	)
)

(instance pump of RFeature
	(properties
		y 56
		x 135
		nsTop 33
		nsLeft 124
		nsBottom 79
		nsRight 147
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/pump]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 84)
					)
					((Said 'examine')
						(Print 37 78)
					)
				)
			)
		)
	)
)

(instance whiteTank of RFeature
	(properties
		y 54
		x 229
		nsTop 44
		nsLeft 214
		nsBottom 63
		nsRight 245
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/tank]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 85)
					)
				)
			)
		)
	)
)

(instance greyTank of RFeature
	(properties
		y 31
		x 186
		nsTop 15
		nsLeft 165
		nsBottom 42
		nsRight 208
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/tank]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 37 86)
					)
				)
			)
		)
	)
)
