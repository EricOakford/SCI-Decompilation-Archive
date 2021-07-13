;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use Intrface)
(use tahiti)
(use SoundSyncWave)
(use InitAllFeatures)
(use SolvePuzzle)
(use GoToSaid)
(use ForCount)
(use LoadMany)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	beachHuts4 0
)
(synonyms
	(kiss embrace squeeze hug)
)

(local
	agent
	local1
	local2
)
(instance beachHuts4 of Room
	(properties
		picture 13
		east 24
		south 16
		west 12
	)
	
	(method (init)
		(super init:)
		(Load SOUND 42)
		(Load SOUND 36)
		(LoadMany VIEW 206 112 212 12 13 213 313)
		(addToPics add: beachLayer eachElementDo: #init doit:)
		(InitAllFeatures)
		(hutDoor2 init:)
		(if
			(or
				(and (ego has: iEarring) (== earRingState earringSEARCHED))
				(not metAgentStacy)
			)
			(hutDoor2 stopUpd:)
		)
		(self
			setRegions: 301 300
			setFeatures:
				hutDoor1
				hut1
				hut2
				westWindowFeat
				windowFeat
				((Clone windowFeat)
					x: 139
					y: 31
					z: 30
					nsTop: 12
					nsBottom: 39
					nsLeft: 126
					nsRight: 144
					heading: 265
					yourself:
				)
				((Clone windowFeat)
					x: 190
					y: 16
					z: 25
					nsTop: 2
					nsBottom: 27
					nsLeft: 184
					nsRight: 199
					heading: 175
					yourself:
				)
				((Clone windowFeat)
					x: 265
					y: 21
					z: 30
					nsTop: 7
					nsBottom: 33
					nsLeft: 254
					nsRight: 278
					heading: 150
					yourself:
				)
		)
		((Clone wave) x: 165 y: 137 init:)
		((Clone wave) x: 290 y: 121 init:)
		((Clone wave) x: 202 y: 132 init:)
		((Clone wave) loop: 1 x: 251 y: 148 init:)
		((Clone wave) x: 295 y: 137 init:)
		((Clone wave) loop: 0 x: 295 y: 150 init:)
		(wave init:)
		(ego init:)
		(switch prevRoomNum
			(west (ego x: 10))
			(14
				(ego posn: 210 59 loop: 1)
				(hutDoor2 setCel: 16 setCycle: BegLoop hutDoor2)
				(HandsOn)
			)
			(11
				((= agent (ScriptID 309 0))
					init:
					view: 212
					illegalBits: 0
					posn: 220 54
					ignoreActors: TRUE
					loop: 1
					heading: 270
					setScript: agentInFrontOfHutScript
					setPri: 3
					z: 1000
				)
				(HandsOff)
				(ego
					view: 213
					ignoreActors:
					ignoreControl: cWHITE
					setLoop: 0
					posn: 210 54
					heading: 90
					cel: 0
					ignoreActors: TRUE
					illegalBits: 0
				)
				(HandsOn)
				(User canControl: FALSE)
				(features add: agentFeature)
			)
			(else 
				(ego
					loop: 1
					y: (if (< (ego y?) 105) 105 else (ego y?))
				)
			)
		)
		(if metAgentStacy
			(earRing init:)
		)
	)
	
	(method (dispose)
		(ego ignoreActors: FALSE)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,beach]')
				(Print 13 0)
			)
		)
	)
)

(instance hutDoor1 of RFeature
	(properties
		y 41
		x 25
		nsTop 8
		nsBottom 59
		nsRight 27
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 13 1)
					)
					((GoToIfSaid self event 13 68 0 13 2))
					((Said 'open')
						(Print 13 3)
					)
					((Said 'unlock')
						(if (ego has: iTahitiKey)
							(Print 13 4)
						else
							(Print 13 5)
						)
					)
					((Said 'knock')
						(Print 13 6)
					)
				)
			)
		)
	)
)

(instance hutDoor2 of Prop
	(properties
		y 54
		x 228
		view 13
		signal fixPriOn
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 13 7)
					)
					((GoToIfSaid self event self 20 0 13 2))
					((Said 'open')
						(Print 13 3)
					)
					((Said 'unlock')
						(if (ego has: iTahitiKey)
							(Print 13 8)
						else
							(Print 13 5)
						)
					)
					((Said 'knock')
						(Print 13 6)
					)
				)
			)
		)
	)
	
	(method (cue)
		(if
			(or
				(and (ego has: iEarring) (== earRingState earringSEARCHED))
				(not metAgentStacy)
			)
			(hutDoor2 stopUpd:)
		)
		(doorSound number: (SoundFX 42) loop: 1 play:)
	)
)

(instance agentInFrontOfHutScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= seconds 3)
			)
			(2
				(ego setCycle: BegLoop self)
			)
			(3
				(Print 13 16)
				(Print 13 17)
				(++ register)
				(User canInput: TRUE)
			)
			(4
				(ego observeControl: cWHITE ignoreActors: FALSE)
				(curRoom newRoom: 14)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'thank/ya')
				(Print 13 9)
			)
			((Said 'go,come<in')
				(if register
					(agent posn: 220 54 loop: 0 z: 0)
					(ego view: 206 setLoop: -1 loop: 0 setCycle: Walk)
					(hutDoor2 setCycle: EndLoop self)
					(doorSound number: (SoundFX 42) loop: 1 play:)
				else
					(Print 13 10)
				)
			)
			((Said 'address[/babe,stacy]')
				(Print 13 11)
			)
			((Said 'kiss[/babe,stacy]')
				(if register
					(Print 13 12)
				else
					(HandsOff)
					(ego
						view: 213
						ignoreActors:
						ignoreControl: cWHITE
						setLoop: 0
						setCycle: EndLoop self
					)
					(SolvePuzzle tahiti #pointFlag $0008 1)
				)
			)
			((Said 'affirmative')
				(if register
					(agent posn: 220 54 loop: 0 z: 0)
					(ego view: 206 setLoop: -1 loop: 0 setCycle: Walk)
					(hutDoor2 setCycle: EndLoop self)
					(doorSound number: (SoundFX 42) loop: 1 play:)
				else
					(Print 13 13)
				)
			)
			((or (Said 'n') (Said 'exit/babe'))
				(Print 13 14)
				(client setScript: agentLeavesAngryScript)
			)
			((Said 'fuck,fondle')
				(Print 13 15)
				(tahiti flags: (| (tahiti flags?) fAskedForSex))
				(client setScript: agentLeavesAngryScript)
			)
		)
	)
)

(instance agentLeavesAngryScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 206
					loop: 0
					setLoop: -1
					setCycle: Walk
					illegalBits: -26624
				)
				(agent view: 212 loop: 1 z: 0)
				(hutDoor2 setCycle: EndLoop self)
				(doorSound number: (SoundFX 42) loop: 1 play:)
			)
			(1
				(agent
					setPri: 1
					setCycle: Walk
					setMotion: MoveTo 260 (agent y?) self
				)
			)
			(2
				(hutDoor2 setCycle: BegLoop self)
				(doorSound number: (SoundFX 36) loop: 1 play:)
			)
			(3
				(hutDoor2 ignoreActors: FALSE)
				(if
					(or
						(and (ego has: iEarring) (== earRingState earringSEARCHED))
						(not metAgentStacy)
					)
					(hutDoor2 stopUpd:)
				)
				(agent dispose:)
				(HandsOn)
			)
		)
	)
)

(instance beachLayer of RPicView
	(properties
		y 102
		x 307
		view 12
		loop 1
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/man,towel]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at][/man]')
						(Print 13 18)
					)
					((Said 'look[<at][/towel]')
						(Print 13 19)
					)
					((Said 'wake,kick,jump,walk[<above][/man]')
						(Print 13 20)
					)
					((Said 'address[/man]')
						(Print 13 21)
					)
					((Said 'kiss[/man]')
						(Print 13 22)
					)
				)
			)
		)
	)
)

(instance wave of SoundSyncWave
	(properties
		y 139
		x 114
		view 112
		loop 2
		cycleSpeed 3
	)
	
	(method (init)
		(super init:)
		(self sound: globalSound)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/wave]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 13 23)
					)
				)
			)
		)
	)
)

(instance westWindowFeat of RFeature
	(properties
		y 27
		x 53
		z 30
		heading 265
		nsTop 12
		nsLeft 42
		nsBottom 38
		nsRight 64
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look[<at,in]/*'))
					((Said 'look>')
						(cond 
							((Said '[<at]')
								(Print 13 24)
							)
							((Said '[<in]')
								(Print 13 25)
							)
						)
					)
				)
			)
		)
	)
)

(instance windowFeat of RFeature
	(properties
		y 29
		x 104
		z 30
		heading 175
		nsTop 14
		nsLeft 91
		nsBottom 41
		nsRight 119
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look[<at,in]/*'))
					((Said 'look>')
						(cond 
							((Said '[<at]')
								(Print 13 24)
							)
							((Said '[<in]')
								(Print 13 26)
							)
						)
					)
				)
			)
		)
	)
)

(instance hut1 of RFeature
	(properties
		y 81
		x 39
		heading 180
		nsBottom 79
		nsRight 162
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 13 27)
					)
				)
			)
		)
	)
)

(instance hut2 of RFeature
	(properties
		y 251
		x 31
		heading 180
		nsLeft 183
		nsBottom 62
		nsRight 319
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 13 27)
					)
				)
			)
		)
	)
)

(instance earRing of Prop
	(properties
		y 79
		x 171
		view 313
		loop 2
		priority 4
		signal fixPriOn
	)
	
	(method (doit)
		(super doit:)
		(if (< (ego distanceTo: self) 30)
			(if (and (not cycler) local2 (not (ego has: iEarring)))
				(self setCycle: ForwardCounter 4 self)
			)
		else
			(= local2 1)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((ego has: iEarring))
			(
				(or
					(Said 'look[<down][/floor]')
					(Said 'look[<at,in]/sand')
				)
				(Print 13 28)
			)
			((Said 'look[<at]/glimmer,sparkle')
				(if (& (tahiti flags?) fTalkedToStacy)
					(Print 13 29)
				else
					(Print 13 30)
				)
			)
			(
				(GoToIfSaid self event self 20
					'get,(pick<up)/earring,glimmer,sparkle,object'
					13 2
				)
			)
			((Said 'get,(pick<up)/earring,glimmer,sparkle,object')
				(Print 13 31)
				(ego get: iEarring)
				(self dispose:)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
		(= local2 0)
	)
)

(instance agentFeature of Feature
	(properties
		y 54
		x 212
	)
	
	(method (handleEvent event)
		(agent handleEvent: event)
	)
)

(instance doorSound of Sound
	(properties
		priority 2
	)
)
