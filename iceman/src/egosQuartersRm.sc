;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use n316)
(use InitAllFeatures)
(use SolvePuzzle)
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
	egosQuartersRm 0
)

(local
	local0 = [
		0 0 0 0 0 0 0 0
		{} {DESTROY} {COONTZ} {DEGREE} {COURSE} {SHIP} {LATITUDE}
		{RUSSIAN} {REDWOOD} {4100} {TACTIC} {WAR} {STEER} {DIRECTLY}
		{ARIZONA} {OCEAN}
		{} {destroy} {coontz} {degree} {course} {ship} {latitude}
		{russian} {redwood} {4100} {tactic} {war} {steer} {directly}
		{arizona} {ocean}
		]
	local55
	lightIsOn
	drawerIsOpen
	[local58 15]
)
(instance egosQuartersRm of Room
	(properties
		picture 31
		west 25
		vanishingX 150
		vanishingY 10
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(= lightIsOn (Random 0 1))
		(ego init: observeControl: cBLUE view: 232 setPri: -1)
		(if (== prevRoomNum 23)
			(ego
				posn: 295 115
				heading: 270
				loop: 1
				setScript: justGotOnSubScript
			)
		else
			(ego
				posn: 23 119
				heading: 90
				loop: 0
				setMotion: MoveTo 72 119
			)
		)
		(LoadMany VIEW 31 431)
		(map init:)
		(wallPic init:)
		(addToPics doit:)
		(entryDoor init: ignoreActors: TRUE stopUpd:)
		(bathDoor init: cel: 0 stopUpd: ignoreActors: TRUE)
		(RemoveInvItems curRoomNum iCodeBook iVernierCaliper)
		(computer init:)
		(credenza init: setPri: 5 stopUpd:)
		(codeBook init: cel: (if (ego has: iCodeBook) 1 else 0))
		(microMeterView init:)
		(drawerView init: hide:)
		(self
			setRegions: rgSubmarine
			setFeatures:
				bunk
				purpleThing
				desk
				drawer
				closet
				couch
				table
				nightstand
				((Clone couch)
					x: 129
					y: 130
					z: 0
					nsLeft: 113
					nsTop: 120
					nsRight: 145
					nsBottom: 140
					yourself:
				)
		)
		(InitAllFeatures)
	)
	
	(method (dispose)
		(ego ignoreControl: cBLUE)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,scene]')
				(if (and (> (ego x?) (bathDoor x?)) (< (ego y?) 120))
					(Print 31 0)
				else
					(Print 31 1)
					(Print 31 2)
				)
			)
		)
	)
)

(instance justGotOnSubScript of Script
	(properties
		seconds 3
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(bathDoor setCycle: EndLoop self)
			)
			(2
				(HandsOff)
				(Print 31 3 #time 5 #at -1 20 #dispose)
				(ego ignoreControl: cBLUE setMotion: MoveTo 245 115 self)
			)
			(3
				(bathDoor setCycle: BegLoop self)
			)
			(4
				(ego observeControl: cBLUE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance entryDoor of Prop
	(properties
		y 114
		x 36
		view 31
		loop 2
	)
	
	(method (doit)
		(super doit:)
		(if (and (== (ego onControl: origin) cGREEN) (not script))
			(HandsOff)
			(self setScript: entryDoorScript)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 31 4)
					)
					((Said 'close')
						(ItIs)
					)
					((> (ego x?) (bathDoor x?)))
					((GoToIfSaid self event 55 123 'open' 31 5))
					((Said 'open')
						(HandsOff)
						(self setScript: entryDoorScript)
					)
				)
			)
		)
	)
)

(instance entryDoorScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: EndLoop self)
			)
			(1
				(if (> (ego x?) (client x?))
					(= register -1)
				else
					(= register 1)
				)
				(ego
					setAvoider: Avoider
					setPri: 8
					setMotion: MoveTo (+ (ego x?) (* 35 register)) (ego y?) self
				)
			)
			(2
				(client setCycle: BegLoop self)
				(HandsOn)
			)
			(3
				(ego setPri: -1)
				(if (< (ego x?) (client x?))
					(cls)
					(curRoom newRoom: (curRoom west?))
				else
					(client stopUpd:)
					(self dispose:)
				)
			)
		)
	)
)

(instance bathDoor of Prop
	(properties
		y 117
		x 279
		view 31
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door<bathroom]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 31 6)
					)
					((Said 'close')
						(ItIs)
					)
					(
						(and
							(< (ego x?) x)
							(GoToIfSaid self event stupidAvoider 3 'open' 31 5)
						)
					)
					((and (>= (ego x?) x) (Said 'open'))
						(self setScript: bathDoorScript)
					)
					((Said 'open')
						(self setScript: bathDoorScript)
					)
				)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance egosInBathRoom of Script

	(method (handleEvent event)
		(cond 
			((Said 'look[<at,around][/room]')
				(Print 31 7)
				(Print 31 8)
			)
			((Said 'look/sink')
				(Print 31 9)
			)
			(
				(or
					(Said 'use/sink')
					(Said 'wash[/hand]')
					(Said 'turn<on/water')
				)
				(Print 31 10)
			)
			((Said 'look/mirror')
				(Print 31 11)
			)
			((Said 'comb/hair')
				(Print 31 12)
			)
			((Said 'look/shower')
				(Print 31 13)
			)
			((Said '(turn<on),use,get/shower')
				(Print 31 14)
			)
			((Said 'look/light')
				(Print 31 15)
				(Print 31 16)
				(Print 31 17)
				(if lightIsOn
					(Print 31 18)
				else
					(Print 31 19)
				)
			)
			((Said 'look/toilet')
				(Print 31 15)
				(Print 31 16)
				(Print 31 20)
			)
			((Said 'flush[/toilet]')
				(cond 
					(local55
						(= local55 0)
						(flushNoise play:)
						(Print 31 21)
						(Print 31 22)
						(theGame changeScore: -3)
					)
					(lightIsOn
						(Print 31 23)
						(= lightIsOn (Random 0 1))
						(= local55 1)
					)
					(else
						(Print 31 24)
						(= lightIsOn (Random 0 1))
					)
				)
			)
			((Said 'sit,use/toilet,bathroom')
				(Print 31 25)
			)
			((or (Said 'leak') (Said 'get//leak'))
				(Print 31 26)
			)
			((or (Said 'crap') (Said 'get//crap'))
				(Print 31 27)
			)
			((Said 'get/*')
				(Print 31 28)
			)
			((Said 'look/*')
				(Print 31 29)
			)
			((Said 'open[/door]>')
				(bathDoor handleEvent: event)
			)
			((Said '*')
				(Print 31 30)
			)
		)
	)
)

(instance flushNoise of Sound
	(properties
		number 49
		priority 2
	)
)

(instance bathDoorScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client setCycle: EndLoop self)
			)
			(1
				(= register (< (ego x?) (client x?)))
				(ego
					illegalBits: 0
					setMotion: MoveTo (if register 298 else 253) 117 self
				)
			)
			(2
				(client setCycle: BegLoop client)
				(HandsOn)
				(ego
					observeControl: cBLUE cWHITE
					setScript: (if (> (ego x?) (client x?)) egosInBathRoom else 0)
				)
				(self dispose:)
			)
		)
	)
)

(instance codeBook of View
	(properties
		y 71
		x 240
		view 431
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/book[<code,decode]]>')
				(cond 
					((Said 'read,open')
						(if (ego has: iCodeBook)
							((inventory at: iCodeBook) showSelf:)
						else
							(event claimed: FALSE)
						)
					)
					((not (credenza cel?)))
					((and cel (not (event modifiers?)) (Said 'look[<at]'))
						(event claimed: FALSE)
					)
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(if cel
							(Print 31 31)
						else
							(Print 31 32)
						)
					)
					((GoToIfSaid self event 235 115 'get' 31 5))
					((Said 'get')
						(if (IsInvItemInRoom curRoomNum iCodeBook)
							(ego setScript: getBookScript)
						else
							(AlreadyTook)
						)
					)
				)
			)
		)
	)
)

(instance getBookScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 131 loop: 0 setCycle: EndLoop self)
			)
			(1
				(Print 31 33)
				(ego get: iCodeBook)
				(codeBook cel: 1 forceUpd:)
				(ego setCycle: BegLoop self)
			)
			(2
				(HandsOn)
				(ego view: 232 loop: 3 setCycle: Walk)
			)
		)
	)
)

(instance putBookScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (credenza cel?))
					(credenza setCycle: EndLoop self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				(ego view: 131 loop: 0 setCycle: EndLoop self)
			)
			(2
				(Print 31 34)
				(ego put: iCodeBook curRoomNum)
				(codeBook cel: 0 forceUpd:)
				(ego setCycle: BegLoop self)
			)
			(3
				(HandsOn)
				(ego view: 232 loop: 3 setCycle: Walk)
			)
		)
	)
)

(instance wallPic of RPicView
	(properties
		y 113
		x 298
		view 31
		cel 1
		priority 9
		signal fixPriOn
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look[<at]/painting'))
			((Said 'look[<at][/wall,painting]')
				(Print 31 35)
			)
		)
	)
)

(instance map of RPicView
	(properties
		y 90
		x 102
		view 31
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look[<at]/map'))
			((Said 'look[<at][/map]')
				(Print 31 36)
			)
			((Said 'move/map,painting')
				(DontNeedTo)
			)
		)
	)
)

(instance purpleThing of RFeature
	(properties
		y 81
		x 249
		nsTop 78
		nsLeft 243
		nsBottom 84
		nsRight 256
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look[<at]/thing[<purple]'))
			((Said 'look[<at]/thing<purple')
				(Print 31 37)
			)
			((Said 'look[<at][/thing]')
				(Print 31 38)
			)
		)
	)
)

(instance desk of RFeature
	(properties
		y 81
		x 249
		nsTop 89
		nsLeft 214
		nsBottom 111
		nsRight 265
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look[<at]/desk'))
			((Said 'look[<at][/desk]')
				(Print 31 39)
			)
		)
	)
)

(instance bunk of RFeature
	(properties
		y 155
		x 256
		nsTop 138
		nsLeft 207
		nsBottom 169
		nsRight 312
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look[<at]/bunk,bed'))
			((Said 'look[<at][/bunk,bed]')
				(Print 31 40)
			)
			((Said 'look<below/bunk,bed')
				(Print 31 41)
			)
			((Said 'sit[<on,down][/bed,bunk]')
				(Print 31 42)
			)
		)
	)
)

(instance drawer of RFeature
	(properties
		y 100
		x 239
		nsTop 89
		nsLeft 214
		nsBottom 111
		nsRight 265
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/drawer]>')
				(cond 
					((TurnIfSaid self event 'look[<at,in]/*'))
					((Said 'look[<at,in]')
						(Print 31 43)
						(cond 
							((or (> (ego distanceTo: self) 40) (not drawerIsOpen)) 0)
							((& (drawerView signal?) hideActor)
								(drawerView show:)
								(if (IsInvItemInRoom curRoomNum iVernierCaliper)
									(Print 31 44)
								else
									(Print 31 45)
								)
							)
							((IsInvItemInRoom curRoomNum iVernierCaliper)
								(Print 31 44)
							)
							(else
								(Print 31 45)
							)
						)
					)
					((GoToIfSaid self event 239 120 0 31 5))
					((Said 'open')
						(ego heading: 0)
						((ego looper?) doit: ego (ego heading?))
						(drawerView show:)
						(if (IsInvItemInRoom curRoomNum iVernierCaliper)
							(Print 31 46)
						else
							(Print 31 47)
						)
						(= drawerIsOpen TRUE)
					)
					((Said 'close')
						(ego heading: 0)
						((ego looper?) doit: ego (ego heading?))
						(drawerView hide:)
						(= drawerIsOpen FALSE)
					)
				)
			)
		)
	)
)

(instance drawerView of View
	(properties
		y 38
		x 238
		view 431
		cel 1
	)
	
	(method (doit)
		(if (CantBeSeen self ego)
			(self hide:)
		)
		(super doit:)
	)
	
	(method (hide)
		(super hide: &rest)
		(|= signal staticView)
		(microMeterView hide:)
	)
	
	(method (show)
		(super show: &rest)
		(&= signal (~ staticView))
		(if (IsInvItemInRoom curRoomNum iVernierCaliper)
			(microMeterView show:)
		)
	)
)

(instance microMeterView of View
	(properties
		y 79
		x 239
		z 52
		view 431
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((not drawerIsOpen))
			((TurnIfSaid self event 'look[<at]/caliper'))
			((GoToIfSaid self event 239 120 'get,adjust/caliper' 31 5))
			((Said 'get/caliper')
				(if (IsInvItemInRoom curRoomNum iVernierCaliper)
					(self hide:)
					(ego get: iVernierCaliper)
					(SolvePuzzle subMarine #pointFlag1 $0001 1)
				else
					(AlreadyTook)
				)
			)
			((Said 'adjust/caliper[<in][/drawer]')
				(if (ego has: iVernierCaliper)
					(ego put: iVernierCaliper curRoomNum)
					(if (not (& (drawerView signal?) hideActor)) (self show:))
				else
					(DontHave)
				)
			)
		)
	)
)

(instance credenza of Prop
	(properties
		y 94
		x 214
		z 40
		view 31
		loop 3
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/bookcase,bookcase,shelf,briefcase<[book]]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at,in]')
						(cond 
							((not cel)
								(Print 31 48)
							)
							((ego has: iCodeBook)
								(Print 31 31)
							)
							(else
								(Print 31 32)
							)
						)
					)
					((GoToIfSaid self event 235 115 0 31 5))
					((Said 'open')
						(if cel
							(ItIs)
						else
							(self setCycle: EndLoop self)
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
			((Said '/book>')
				(cond 
					((not (ego has: iCodeBook)))
					((TurnIfSaid self event 'adjust'))
					((GoToIfSaid self event 235 115 'adjust' 31 5))
					((Said 'adjust')
						(ego setScript: putBookScript)
					)
				)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance computer of Prop
	(properties
		y 91
		x 232
		view 31
		cel 3
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/computer]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 31 49)
					)
					((GoToIfSaid self event 232 115 0 31 5))
					((Said 'use,(turn<on)/*')
						(if script
							(Print 31 50)
						else
							(computer setScript: (ScriptID 386))
						)
					)
				)
			)
			((Said 'decode')
				(Print 31 51)
			)
		)
	)
)

(instance closet of RFeature
	(properties
		y 83
		x 190
		nsTop 54
		nsLeft 167
		nsBottom 113
		nsRight 214
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/closet,cabinet]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 31 52)
					)
					((Said 'unlock,open,(look<in)')
						(Print 31 53)
					)
					((Said 'close')
						(ItIs)
					)
				)
			)
		)
	)
)

(instance couch of RFeature
	(properties
		y 158
		x 103
		nsTop 141
		nsLeft 61
		nsBottom 176
		nsRight 145
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/couch]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 31 54)
					)
					((Said 'sit[<down,on][/couch]')
						(Print 31 42)
					)
				)
			)
		)
	)
)

(instance table of RFeature
	(properties
		y 132
		x 76
		nsTop 124
		nsLeft 56
		nsBottom 140
		nsRight 96
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/table]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 31 55)
					)
					((Said 'look<below')
						(Print 31 56)
					)
				)
			)
		)
	)
)

(instance nightstand of RFeature
	(properties
		y 127
		x 277
		nsTop 117
		nsLeft 261
		nsBottom 137
		nsRight 294
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/dresser]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 31 57)
					)
					((Said 'open')
						(DontNeedTo)
					)
					((Said 'close')
						(ItIs)
					)
				)
			)
		)
	)
)

(instance stupidAvoider of Feature
	(properties
		y 117
		x 253
	)
)
