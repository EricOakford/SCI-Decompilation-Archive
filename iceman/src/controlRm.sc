;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include sci.sh)
(use Main)
(use Intrface)
(use subMarine)
(use n385)
(use Submarine_806)
(use EgoDead)
(use NormalEgo)
(use SolvePuzzle)
(use GoToSaid)
(use LoadMany)
(use QScript)
(use RFeature)
(use Avoider)
(use Extra)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	controlRm 0
	captain 1
	sitControlPanelScript 2
	egosControlScript 3
	controlPanelChair 4
	goToANewRoomScript 5
	ladderFeat 6
	scopeProp2 7
)

(local
	local0
)
(instance controlRm of Rm
	(properties
		picture 25
	)
	
	(method (init)
		(super init:)
		(LoadMany 128 232 133 25 125 225 525)
		(LoadMany 130 385 390 391 393 394)
		(ego init: setScript: egosControlScript)
		(NormalEgo)
		(HandsOn)
		(addToPics
			add:
				rail1Pic
				rail2Pic
				chairPic
				mapPic
				cornerPic
				mapTable
				scopePic
				mapOnWallPic
				crewMember
				scopePic2
				controlPanelPic
				poleToRailPic
			doit:
		)
		(self
			setFeatures: mapTable ladderFeat crewMember stairFeat mapOnWallPic
			setRegions: 314
		)
		(switch prevRoomNum
			(27
				(ego
					setScript:
						(switch ((subMarine script?) state?)
							(1 shouldHaveDroveScript)
							(2 (ScriptID 395))
							(7 (ScriptID 345))
							(15 (ScriptID 338))
							(else  standUpScript)
						)
				)
			)
			(28
				(self setScript: (ScriptID 391 1))
			)
			(26
				(if (== ((subMarine script?) state?) 15)
					(captain init: setScript: (ScriptID 337))
				)
				((if (== (ego x?) 104) scopeProp else scopeProp2)
					setScript: downScopeScript
				)
			)
			(31
				(ego
					posn: 45 178
					heading: 0
					loop: 6
					setScript:
						(if (== ((subMarine script?) state?) 0)
							(ScriptID 390)
						else
							egosControlScript
						)
				)
			)
			(30
				(ego posn: 25 177 heading: 90 loop: 0)
			)
			(40
				(if (== ((subMarine script?) state?) 5)
					(captain
						init:
						posn: 214 100
						heading: 180
						loop: 2
						illegalBits: 0
					)
					(ego setScript: (ScriptID 336))
				)
			)
			(else 
				(ego view: 232 posn: 69 100 loop: 0 heading: 90)
				(if
					(and
						(== ((subMarine script?) state?) 6)
						(ego has: 13)
					)
					(self setScript: (ScriptID 346))
				)
			)
		)
		(if
			(not
				(= local0
					(if
						(OneOf
							((subMarine script?) state?)
							0
							2
							5
							6
							7
							14
							11
							9
							10
							13
							14
							15
							8
						)
					else
						(== prevRoomNum 27)
					)
				)
			)
			(ego observeControl: 8192)
		)
		(if (!= prevRoomNum 27) (controlPanelChair init:))
		(scopeProp init:)
		(scopeProp2 init:)
		(leftArms init:)
		(rightArms init: isExtra: 1)
		(turningProp init: setCycle: Fwd isExtra: 1)
		(rowOfLights init: setCycle: Fwd isExtra: 1)
		(scanningProp init: setCycle: Fwd isExtra: 1)
		(viewerProp init: setCycle: Fwd isExtra: 1)
		(rotateCap init: setCycle: Fwd isExtra: 1)
		(redCap init: setCycle: Fwd isExtra: 1)
		(redCap2 init: setCycle: Fwd isExtra: 1)
		(rotatingGauge init: setCycle: Fwd isExtra: 1)
		(rotatingGauge2 init: setCycle: Fwd isExtra: 1)
		(rotatingGauge3 init: setCycle: Fwd isExtra: 1)
		(anotherGauge init: setCycle: Fwd isExtra: 1)
	)
	
	(method (dispose)
		(LoadMany 0 395 391 393 390 394)
		(cls)
		(ego illegalBits: -32768)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<around][/room]') (Print 25 0) (Print 25 1) (Print 25 2))
			((Said 'examine,cycle/equipment') (Print 25 3))
			((Said 'look,open,read/book[<code]')
				(if (ego has: 14)
					((inventory at: 14) showSelf:)
				else
					(DontHave)
				)
			)
		)
	)
)

(instance goToANewRoomScript of Script
	(properties
		cycles 2
	)
	
	(method (changeState newState)
		(if (= state newState) (curRoom newRoom: register))
	)
)

(instance standUpScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScript: (ScriptID 394) self)
			)
			(1
				(ego setScript: egosControlScript)
			)
		)
	)
)

(instance captain of Act
	(properties
		view 133
	)
)

(instance egosControlScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (User controls?)
			(switch (ego onControl: 1)
				(-32768 (return))
				(16384
					(ego setScript: walkDownStairsScript)
				)
				(4096
					(ego setScript: (ScriptID 393))
				)
				(8192
					(ego setScript: sitControlPanelScript)
				)
				(2048 (curRoom newRoom: 31))
				(1024 (curRoom newRoom: 30))
			)
		)
	)
)

(instance walkDownStairsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreControl: -16384 setMotion: MoveTo 67 103 self)
			)
			(1
				(ego setMotion: MoveTo 43 120 self)
			)
			(2 (curRoom newRoom: 32))
		)
	)
)

(instance sitControlPanelScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 187 80 self
				)
			)
			(1
				(ego loop: 6 cel: 3)
				(= cycles 2)
			)
			(2
				(controlPanelChair hide:)
				(ego
					view: 325
					loop: 1
					cel: 0
					posn: 209 80
					setCycle: End self
				)
			)
			(3 (= cycles 2))
			(4 (curRoom newRoom: 27))
		)
	)
)

(instance mapTable of RPicView
	(properties
		y 146
		x 159
		view 25
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/map,table,course[<map,chart]]>')
				(cond 
					((TurnIfSaid self event))
					((GoToIfSaid self event self 30 0 25 4))
					((Said 'look,draw,use') (curRoom newRoom: 40))
				)
			)
		)
	)
)

(instance mapOnWallPic of RPicView
	(properties
		y 94
		x 270
		z 20
		view 25
		loop 1
		cel 2
		priority 1
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/map,chart]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 25 5))
				)
			)
		)
	)
)

(instance scopePic2 of PV
	(properties
		y 112
		x 110
		view 25
		loop 2
		priority 7
	)
)

(instance scopePic of PV
	(properties
		y 100
		x 144
		view 25
		loop 2
		cel 1
		priority 6
	)
)

(instance scopeProp of Prop
	(properties
		y 112
		x 110
		view 25
		loop 3
		priority 7
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(or (Said 'raise,up/periscope') (Said 'periscope<use'))
				(cond 
					((scopeProp2 cel?) (Print 25 6))
					((& (Submarine flags?) $0010) (Print 25 7))
					((>= (Submarine _depth?) 70) (Print 25 8))
					((<= (Submarine hSpeed?) 12)
						(if cel
							(Print 25 9)
						else
							(HandsOff)
							(self setCycle: End self)
						)
					)
					((& (subMarine roomFlags?) $0200)
						(Print 25 10)
						(Submarine flags: (| (Submarine flags?) $0010))
					)
					(else
						(Print 25 11)
						(subMarine roomFlags: (| (subMarine roomFlags?) $0200))
					)
				)
			)
			((Said 'lower,down/periscope')
				(if cel
					(HandsOff)
					(self setCycle: Beg self)
					(HandsOff)
				else
					(Print 25 12)
				)
			)
			((Said 'look/periscope>')
				(if (or cel (Said 'look<through'))
					(self setScript: lookScopeScript)
				else
					(Print 25 13)
				)
				(event claimed: 1)
			)
		)
	)
	
	(method (cue)
		(HandsOn)
		(if cel (QueScript self watchSpeedScript 0 12))
		(self stopUpd:)
	)
)

(instance scopeProp2 of Prop
	(properties
		y 100
		x 145
		view 25
		loop 3
		priority 6
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(or (Said 'raise,up/periscope') (Said 'periscope<use'))
				(cond 
					((scopeProp cel?) (Print 25 6))
					((& (Submarine flags?) $0020) (Print 25 14))
					((>= (Submarine _depth?) 70) (Print 25 8))
					((<= (Submarine hSpeed?) 5)
						(if cel
							(Print 25 9)
						else
							(HandsOff)
							(self setCycle: End self)
						)
					)
					((& (subMarine roomFlags?) $0100)
						(Print 25 15)
						(Submarine flags: (| (Submarine flags?) $0020))
					)
					(else
						(Print 25 16)
						(subMarine roomFlags: (| (subMarine roomFlags?) $0100))
					)
				)
			)
			((Said 'lower,down/periscope')
				(if cel
					(HandsOff)
					(self setCycle: Beg self)
				else
					(Print 25 12)
				)
			)
			((Said 'look/periscope>')
				(if (or cel (Said 'look<through'))
					(self setScript: lookScopeScript)
				else
					(Print 25 13)
				)
				(event claimed: 1)
			)
		)
	)
	
	(method (cue)
		(HandsOn)
		(if cel (QueScript self watchSpeedScript 0 5))
		(self stopUpd:)
	)
)

(instance mapPic of PV
	(properties
		y 98
		x 56
		view 25
		loop 1
		priority 0
		signal $4000
	)
)

(instance cornerPic of PV
	(properties
		y 75
		x 130
		view 25
		loop 1
		cel 1
		priority 0
		signal $4000
	)
)

(instance chairPic of PV
	(properties
		y 89
		x 72
		view 25
		cel 1
		priority 5
		signal $4000
	)
)

(instance rail1Pic of PV
	(properties
		y 108
		x 30
		view 25
		cel 2
		priority 7
		signal $4000
	)
)

(instance rail2Pic of PV
	(properties
		y 116
		x 58
		view 25
		cel 3
		priority 8
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[/stair]') (Print 25 17))
		)
	)
)

(instance poleToRailPic of PV
	(properties
		y 108
		x 73
		view 25
		cel 5
		priority 7
	)
)

(instance turningProp of Prop
	(properties
		y 48
		x 84
		view 125
		priority 3
		signal $4000
	)
)

(instance rowOfLights of Prop
	(properties
		y 65
		x 54
		view 125
		loop 1
		priority 3
		signal $4000
	)
)

(instance scanningProp of Prop
	(properties
		y 74
		x 30
		view 125
		loop 2
		priority 3
		signal $4000
	)
)

(instance viewerProp of Prop
	(properties
		y 74
		x 17
		view 125
		loop 3
		priority 3
		signal $4000
	)
)

(instance rotateCap of Extra
	(properties
		y 54
		x 45
		view 125
		loop 4
		priority 3
		signal $4200
		maxPause 100
		minCycles 10
		maxCycles 60
	)
)

(instance redCap2 of Extra
	(properties
		y 53
		x 55
		view 125
		loop 6
		priority 2
		signal $4200
		maxPause 100
		minCycles 10
		maxCycles 60
	)
)

(instance redCap of Extra
	(properties
		y 52
		x 64
		view 125
		loop 5
		priority 3
		signal $4200
		maxPause 100
		minCycles 10
		maxCycles 60
	)
)

(instance leftArms of Prop
	(properties
		view 25
		loop 6
	)
	
	(method (init)
		(super init:)
		(self
			setScript: sonarMansArmScript
			setPri: 4
			posn: (- (chairPic x?) 4) (- (chairPic y?) 18)
			ignoreActors: 1
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said '/harbor,rig,distance>')
					(Said '[/sonarman,man[<sonar]]>')
				)
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look') (Print 25 18))
					((GoToIfSaid self event self 40 0 25 4))
					((Said 'address')
						(if (== ((subMarine script?) state?) 15)
							(Print 25 19)
						else
							(Print 25 20)
						)
					)
					((Said 'get/distance/!*') (Print 25 21))
					(
						(or
							(Said 'ping/rig[<oil]')
							(Said 'get/distance/rig[<oil]')
						)
						(if (== ((subMarine script?) state?) 15)
							(Print 25 22)
							(Print 25 23)
							(SolvePuzzle subMarine 407 256 1)
						else
							(Print 25 24)
						)
					)
					(
					(or (Said 'ping/harbor') (Said 'get/distance/harbor'))
						(if (== ((subMarine script?) state?) 15)
							(Print 25 25)
							(Print 25 26)
							(SolvePuzzle subMarine 407 512 1)
						else
							(Print 25 24)
						)
					)
				)
			)
		)
	)
)

(instance rightArms of Prop
	(properties
		view 25
		loop 7
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 4
			posn: (- (chairPic x?) 4) (- (chairPic y?) 18)
			ignoreActors: 1
		)
	)
	
	(method (addToPic)
		(leftArms
			setScript: 0
			stopUpd:
			signal: (| (leftArms signal?) $0100)
		)
		(super addToPic: &rest)
	)
)

(instance crewMember of RPicView
	(properties
		y 76
		x 129
		view 25
		cel 4
		priority 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'read,decode/message') (Print 25 27))
			(
			(Said '[/message,radioman,operator,man[<radio]]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((GoToIfSaid self event self 30 0 25 4))
					((Said 'look[<at]') (Print 25 28))
					((or (Said 'address') (Said '/message'))
						(Print 25 29)
						(if
						(+ (proc385_0 6 0) (proc385_0 10 4) (proc385_0 13 8))
							(switch ((subMarine script?) state?)
								(6
									(SolvePuzzle subMarine 406 -32768 1)
								)
								(10
									(SolvePuzzle subMarine 407 64 1)
									(subMarine cue:)
								)
								(13
									(SolvePuzzle subMarine 407 128 1)
									(subMarine cue:)
								)
							)
						else
							(Print 25 30)
						)
						(DisposeScript 385)
					)
				)
			)
		)
	)
)

(instance controlPanelChair of View
	(properties
		y 80
		x 209
		view 125
		loop 8
		priority 5
	)
	
	(method (init)
		(super init: &rest)
		(if local0
			(self view: 125 loop: 8 cel: 0)
		else
			(self view: 325 loop: 1 cel: 8)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/chair,panel[<control]]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 25 31))
					((GoToIfSaid self event self 20 'sit' 25 4))
					((Said 'sit')
						(if local0
							(ego setScript: sitControlPanelScript)
						else
							(Print 25 32)
						)
					)
				)
			)
		)
	)
)

(instance controlPanelPic of PV
	(properties
		y 92
		x 203
		view 225
		priority 4
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[/panel,console]') (Print 25 33))
		)
	)
)

(instance sonarMansArmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rightArms stopUpd:)
				(leftArms stopUpd:)
				(= seconds (Random 5 10))
			)
			(1
				(rightArms hide:)
				(leftArms show: setCycle: End self)
			)
			(2
				(leftArms stopUpd:)
				(= seconds (Random 1 4))
			)
			(3
				(leftArms setCycle: Beg self)
			)
			(4
				(leftArms stopUpd:)
				(= seconds (Random 5 10))
			)
			(5
				(leftArms hide:)
				(rightArms show: setCycle: End self)
			)
			(6
				(rightArms stopUpd:)
				(= seconds (Random 1 4))
			)
			(7
				(rightArms setCycle: Beg self)
			)
			(8 (self init: client))
		)
	)
)

(instance lookScopeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setAvoider: Avoid
					setMotion: MoveTo (- (client x?) 6) (+ (client y?) 4) self
				)
			)
			(1
				(if (client cel?)
					(= cycles 1)
				else
					(client setCycle: End self)
				)
			)
			(2
				(client stopUpd:)
				(ego
					view: 25
					loop: 4
					heading: 0
					cel: 0
					setAvoider: 0
					setCycle: End self
				)
			)
			(3 (curRoom newRoom: 26))
		)
	)
)

(instance downScopeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client setCel: (client lastCel:))
				(ego setCycle: Beg self)
			)
			(1
				(ego view: 232 loop: 3 setCycle: Walk)
				(client setCycle: Beg self)
			)
			(2
				(HandsOn)
				(client stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance rotatingGauge of Prop
	(properties
		y 48
		x 74
		view 125
		loop 7
		priority 1
		signal $4000
		cycleSpeed 2
	)
)

(instance rotatingGauge2 of Prop
	(properties
		y 58
		x 25
		view 125
		loop 9
		priority 2
		signal $4000
	)
)

(instance rotatingGauge3 of Extra
	(properties
		y 60
		x 92
		view 525
		priority 2
		signal $4200
		maxPause 100
		minCycles 10
		maxCycles 60
	)
)

(instance anotherGauge of Prop
	(properties
		y 56
		x 125
		view 25
		loop 8
		priority 2
		signal $4000
	)
)

(instance shouldHaveDroveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScript: (ScriptID 394) self)
			)
			(1 (EgoDead 918 0 0 25 34))
		)
	)
)

(instance ladderFeat of Feature
	(properties
		y 90
		x 298
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/ladder]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look') (Print 25 35))
					((GoToIfSaid self event x y 'climb[<up]' 25 4))
					((Said 'climb[<up]') (ego setScript: (ScriptID 393)))
				)
			)
		)
	)
)

(instance stairFeat of Feature
	(properties
		y 98
		x 76
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '/stair>')
				(cond 
					(
						(GoToIfSaid
							self
							event
							self
							2
							'walk,climb,down[<down]/*'
							25
							4
						)
					)
					((Said 'walk,climb,down[<down]/*') (ego setMotion: MoveTo 67 103))
				)
			)
		)
	)
)

(instance watchSpeedScript of Script
	(properties)
	
	(method (doit)
		(if
		(and (not state) (> (Submarine hSpeed?) register))
			(self cue:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(Print 25 36)
				(if (== register 12)
					(subMarine roomFlags: (| (subMarine roomFlags?) $0200))
					(Submarine flags: (| (Submarine flags?) $0010))
				else
					(subMarine roomFlags: (| (subMarine roomFlags?) $0100))
					(Submarine flags: (| (Submarine flags?) $0020))
				)
				(client setCycle: Beg client)
			)
		)
	)
)
