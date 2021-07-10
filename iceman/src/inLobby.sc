;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include sci.sh)
(use Main)
(use Intrface)
(use tahiti)
(use n316)
(use n361)
(use InitAllFeatures)
(use SolvePuzzle)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	inLobby 0
)

(local
	local0
	local1
)
(instance inLobby of Rm
	(properties
		picture 10
		east 11
		south 9
		picAngle 70
		vanishingX 116
		vanishingY 36
	)
	
	(method (init)
		(super init:)
		(clerk init:)
		(doorToBar init:)
		(girlInLounge init:)
		(addToPics
			add: signPic picturePic barSignPic
			eachElementDo: #init
			doit:
		)
		(Load rsSOUND 42)
		(Load rsSOUND 36)
		(LoadMany 128 10 910 110 310)
		(ego init:)
		(self
			setRegions: 300
			setFeatures: flowersFeature plantFeature mailRFeat girlRFeature
		)
		(switch prevRoomNum
			(east
				(ego posn: 280 113 setMotion: MoveTo 200 113)
			)
			(else 
				(HandsOn)
				(ego posn: 116 176)
			)
		)
		(InitAllFeatures)
		(RemoveInvItems curRoomNum 5)
	)
	
	(method (doit)
		(super doit:)
		(if (> (ego y?) 180) (self newRoom: south))
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,scene,lobby]') (Print 10 0))
			((Said 'look<up') (Print 10 1))
		)
	)
	
	(method (cue)
		(super cue:)
		(self newRoom: 11)
	)
)

(instance signPic of RPicView
	(properties
		y 98
		x 174
		z 20
		view 10
		loop 3
		priority 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/sign,wall]>')
				(cond 
					((TurnIfSaid self event 'read,look[<at]/*'))
					((Said 'read,look[<at]')
						(if (> (ego distanceTo: self) 60)
							(Print 10 2)
						else
							(signCloseUp init:)
						)
					)
				)
			)
		)
	)
)

(instance signCloseUp of View
	(properties
		y 58
		x 164
		view 310
	)
	
	(method (init)
		(super init:)
		(self setPri: 15)
		(= signal (& signal $feff))
		(keyDownHandler add: self)
		(SolvePuzzle tahiti 413 4 1)
	)
	
	(method (doit)
		(super doit:)
		(if (> (ego distanceTo: signPic) 60) (self dispose:))
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(or
				(== (event type?) evMOUSEBUTTON)
				(== (event type?) evKEYBOARD)
			)
			(self dispose:)
		)
	)
)

(instance picturePic of RPicView
	(properties
		y 124
		x 43
		z 30
		view 10
		loop 2
		priority 5
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/painting,(wall<left)]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]') (Print 10 3))
				)
			)
		)
	)
)

(instance barSignPic of RPicView
	(properties
		y 139
		x 267
		z 28
		view 10
		loop 5
		priority 9
		signal $4000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/sign,(wall<right)]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 10 4))
				)
			)
		)
	)
)

(instance doorToBar of Prop
	(properties
		y 120
		x 238
		view 10
		loop 4
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: 1 setPri: 6 stopUpd:)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (== (ego onControl: 1) 512) (not (self script?)))
			(HandsOff)
			(self setScript: barDoorScript)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]') (Print 10 5))
					((Said 'close') (Print 10 6))
					((GoToIfSaid self event 229 115 'open' 10 7))
					((Said 'open') (return))
				)
			)
		)
	)
)

(instance barDoorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: End self)
				(theSound number: (SoundFX 36) loop: 1 play:)
			)
			(1
				(if (> (ego x?) (client x?))
					(= register -1)
				else
					(= register 1)
				)
				(ego
					illegalBits: (if (== register 1) 0 else -32768)
					setMotion: MoveTo (+ (ego x?) (* 45 register)) (ego y?) self
				)
			)
			(2
				(ego illegalBits: -32768)
				(client setCycle: Beg self)
				(theSound number: (SoundFX 42) loop: 1 play:)
			)
			(3
				(if (== register 1)
					(HandsOn)
					(curRoom newRoom: 11)
				else
					(if (not (curRoom script?)) (HandsOn))
					(self dispose:)
				)
			)
		)
	)
)

(instance girlInLounge of Extra
	(properties
		y 164
		x 268
		view 10
		cycleType 2
		hesitation 15
		minPause 70
		maxPause 110
		minCycles 1
		maxCycles 1
	)
	
	(method (handleEvent event)
		(girlRFeature handleEvent: event)
	)
)

(instance girlRFeature of RFeature
	(properties
		y 163
		x 267
		z 10
		nsTop 144
		nsLeft 257
		nsBottom 162
		nsRight 278
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/babe,cunt,boob]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 10 8))
					((GoToIfSaid self event self 40 0 10 7))
					((Said 'address') (Print 10 9))
					(
						(or
							(Said 'kiss')
							(Said '/kiss')
							(Said '//kiss')
							(Said 'ask/babe/date')
						)
						(Print 10 10)
					)
					((Said 'buy,order/drink[<babe][/babe]') (Print 10 11))
					((Said 'fuck,suck,hug') (Print 10 12))
				)
			)
			((Said 'look[<at]/chair') (Print 10 13))
		)
	)
)

(instance clerk of Act
	(properties
		y 106
		x 118
		z 20
		view 910
	)
	
	(method (init)
		(super init:)
		(self setPri: 6 stopUpd:)
		(if
			(and
				(& (tahiti flags?) $0008)
				(not (& (tahiti flags?) $0100))
			)
			(= local1 (Random 50 100))
		)
	)
	
	(method (doit)
		(super doit:)
		(if (and local1 (not (-- local1))) (self cue:))
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'ask,get/time') (Print 10 14))
			((Said '[/babe,babe,clerk,ya,cunt,boob]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(if (> (ego distanceTo: self) 30)
							(Print 10 15)
						else
							(AddExtras 110 eyesExtra lipsExtra)
						)
					)
					((GoToIfSaid self event self 20 0 10 7))
					((Said 'fuck,suck') (Print 10 16))
					((Said '/cunt,boob') (Print 10 12))
					((Said 'address')
						(switch (Random 0 2)
							(0 (Print 10 17))
							(1 (Print 10 18))
							(2 (Print 10 19))
						)
						(SolvePuzzle tahiti 413 32 1)
					)
					((Said 'thank') (Print 10 20))
					(
						(or
							(Said 'kiss')
							(Said '/kiss')
							(Said '//kiss')
							(Said 'ask/babe/date')
						)
						(Print 10 21)
					)
					(
					(Said 'buy,order/drink[<babe,clerk][/babe,clerk]') (Print 10 22))
					(
						(or
							(Said 'get,call,dial,(pick<up)/call')
							(Said '(call<use)')
							(Said 'make/call<call')
							(Said '//call<about,for')
						)
						(Print 10 23)
					)
				)
			)
			(
			(TurnIfSaid self event '[/headband,message,note]'))
			((Said 'look[<at]/headband') (Print 10 24))
			((Said '/message,note,mail>')
				(cond 
					((not (& (tahiti flags?) $0008)) (DontNeedTo) (event claimed: 1))
					((TurnIfSaid self event 'look[<at]/*'))
					(
						(GoToIfSaid
							self
							event
							self
							20
							'get,get,read,look[<at]'
							10
							7
						)
					)
					((Said 'get,get,read,look[<at]')
						(cond 
							((& (tahiti flags?) $0100) (AlreadyTook))
							(local0
								(Print 10 25)
								(Print 10 26)
								(Print 10 27)
								(theGame changeScore: 1)
								(tahiti flags: (| (tahiti flags?) $0100))
							)
							(else (event claimed: 0))
						)
					)
				)
			)
			((or (Said '/bar') (Said '//bar')) (Print 10 28))
			((or (Said '/transport') (Said '//transport')) (Print 10 29))
			((Said 'get/information') (Print 10 30))
			((Said 'pay/bill,tab,room') (Print 10 31))
			((Said '/key,room>')
				(cond 
					(
						(GoToIfSaid
							self
							event
							self
							20
							'get,get,rent,buy,need,reserve,adjust,give,return,drop'
							10
							7
						)
					)
					((Said 'get,get,rent,buy,need,reserve')
						(if (IsInvItemInRoom curRoomNum 5)
							(self setScript: keyScript 0 0)
							(ego get: 5)
							(SolvePuzzle tahiti 413 2 1)
						else
							(AlreadyTook)
						)
					)
					((Said 'adjust,give,return,drop')
						(if (ego has: 5)
							(ego put: 5 curRoomNum)
							(self setScript: keyScript 0 1)
						else
							(DontHave)
						)
					)
				)
			)
		)
	)
	
	(method (cue)
		(super cue: &rest)
		(if
			(and
				(not (& (tahiti flags?) $0100))
				(& (tahiti flags?) $0008)
			)
			(= local0 1)
			(Print 10 32 #time 10)
			(Print 10 33)
			(tahiti setScript: (ScriptID 300 2))
		)
	)
)

(instance keyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register (Print 10 34))
				(clerk loop: 0 setCycle: End self)
			)
			(1 (= seconds 2))
			(2
				(clerk loop: 1 setCycle: End self)
			)
			(3
				(clerk loop: 0 cel: 0)
				(if (not register) (Print 10 35))
			)
		)
	)
)

(instance eyesExtra of Extra
	(properties
		y 83
		x 152
		view 110
		cycleType 2
		hesitation 10
		minPause 50
		maxPause 50
		minCycles 1
		maxCycles 1
	)
)

(instance lipsExtra of Extra
	(properties
		y 132
		x 155
		view 110
		loop 1
		cycleType 2
		hesitation 10
		minPause 50
		maxPause 50
		minCycles 1
		maxCycles 1
	)
)

(instance flowersFeature of RFeature
	(properties
		y 179
		x 227
		z 20
		nsTop 139
		nsLeft 217
		nsBottom 159
		nsRight 237
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/flower,centerpiece,table]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 10 36))
					((GoToIfSaid self event self 50 0 10 7))
					((Said 'smell') (Print 10 37))
					((Said 'get') (Print 10 38))
				)
			)
		)
	)
)

(instance plantFeature of RFeature
	(properties
		y 101
		x 204
		nsTop 71
		nsLeft 194
		nsBottom 101
		nsRight 214
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/plant,fern]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]') (Print 10 39))
					((GoToIfSaid self event self 40 0 10 7))
					((Said 'move,get') (DontNeedTo))
					((Said 'smell') (Print 10 40))
				)
			)
		)
	)
)

(instance mailRFeat of RFeature
	(properties
		y 65
		x 100
		nsTop 60
		nsLeft 84
		nsBottom 75
		nsRight 111
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(Said '[/cubbyhole,box,(wall<center,medium,rear)]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'check,look[<at]') (Print 10 41))
				)
			)
		)
	)
)

(instance theSound of Sound
	(properties
		priority 2
	)
)
