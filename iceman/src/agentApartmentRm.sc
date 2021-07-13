;;; Sierra Script 1.0 - (do not remove this comment)
(script# 84)
(include game.sh)
(use Main)
(use Intrface)
(use tunisia)
(use SolvePuzzle)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	agentApartmentRm 0
)
(synonyms
	(cabinet cupboard)
	(canister jar can)
)

(local
	[str 20]
	tunisiaBagTimer =  10000
	local21
	local22
)
(procedure (localproc_000c param1)
	(return
		(not
			(if
				(and
					(< 0 (param1 x?))
					(< (param1 x?) 319)
					(< 0 (- (param1 y?) (param1 z?)))
				)
				(< (- (param1 y?) (param1 z?)) 189)
			else
				0
			)
		)
	)
)

(procedure (localproc_003c param1)
	(switch param1
		(0
			(sugar show: setScript: 0)
			(flour show: setScript: 0)
			(coffee show: setScript: 0)
			(DisposeScript 363)
			(ego put: 9 put: 10 put: 11)
			(= local21 0)
		)
		(sugar
			(ego get: 9 put: 10 put: 11)
			(flour show: setScript: 0)
			(coffee show: setScript: 0)
			(sugar hide: setScript: (ScriptID 363 0))
			(= local21 1)
		)
		(flour
			(ego get: 10 put: 9 put: 11)
			(sugar show: setScript: 0)
			(coffee show: setScript: 0)
			(flour hide: setScript: (ScriptID 363 1))
			(= local21 1)
		)
		(coffee
			(ego get: 11 put: 9 put: 10)
			(sugar show: setScript: 0)
			(flour show: setScript: 0)
			(coffee hide: setScript: (ScriptID 363 2))
			(= local21 1)
		)
	)
)

(instance agentApartmentRm of Room
	(properties
		picture 84
		north 85
		south 80
		vanishingX 174
		vanishingY -4
	)
	
	(method (init)
		(super init:)
		(LoadMany VIEW 284 684 250 584 85 884 784 984)
		(Load SOUND 67)
		(globalSound
			number: 67
			owner: theGame
			priority: 1
			loop: -1
			play:
		)
		(self
			setRegions: 310
			setFeatures: windowski sink kitchen kounter bed cabinet northWall door
		)
		(LoadMany SOUND 362 365 363 369 319)
		(fridge init:)
		(sugar init:)
		(flour init:)
		(coffee init:)
		(phone init: stopUpd:)
		(if (not (ego has: 12)) (card init: stopUpd:))
		(if (not (ego has: 7)) (tape init: setPri: 15))
		(addToPics add: fridgeSide doit:)
		(switch prevRoomNum
			(north
				(= tunisiaBagTimer (tunisia bagTimer?))
				(if (tunisia bagBound?)
					((ScriptID 310 4) init:)
					((ScriptID 310 6) init:)
				)
				(ego init:)
			)
			(else 
				(ego
					view: 84
					posn: 113 150
					loop: 3
					observeControl: cYELLOW
					init:
				)
			)
		)
		(HandsOn)
	)
	
	(method (doit)
		(super doit:)
		(if (== (ego onControl: origin) cBLUE)
			(curRoom newRoom: 80)
		)
		(switch tunisiaBagTimer
			(0)
			(1
				(= tunisiaBagTimer 0)
				(self setScript: tossEverythingScript)
			)
			(10000)
			(else
				(-- tunisiaBagTimer)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<around][/room]')
				(Print 84 0)
			)
			((Said 'examine/room,apartment')
				(Print 84 1)
			)
			((Said 'eat/food')
				(if (== (tunisia fridgeIs?) 0)
					(CantSee)
				else
					(Print 84 2)
				)
			)
			((Said 'read/note,newspaper')
				(if (ego has: iNote)
					(Print 84 3)
					(Print 84 4)
					(Print 84 5)
					(SolvePuzzle tunisia #pointFlag $0004 4)
				else
					(DontHave)
				)
			)
			((Said 'load/gun')
				(if (ego has: iTranquilizerGun)
					(Print 84 6)
				else
					(event claimed: FALSE)
				)
			)
			(
				(or
					(and
						(== (event type?) keyDown)
						(== (event message?) `#a)
					)
					(Said 'shoot')
					(Said 'shoot,use,fire/gun')
					(Said 'kill,shoot/bagdad,man')
				)
				(cond 
					((not (ego has: iTranquilizerGun))
						(Print 84 7)
					)
					((tunisia bagBound?)
						(Print 84 8)
					)
					(else
						(BadIdea)
					)
				)
			)
			((Said 'read/card[<business]')
				(if (ego has: iBusinessCard)
					(Print 84 9 #icon 384 1 1)
				else
					(Print 84 10)
				)
			)
			((Said 'address/man,bagdad')
				(if (tunisia bagBound?)
					(Print 84 11)
				else
					(Print 84 12)
				)
			)
			((Said 'hit/man,bagdad[/gun<with]')
				(if (tunisia bagBound?)
					(Print 84 13)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'conceal,adjust<in,below/gun/hat')
				(if (ego has: iTranquilizerGun)
					(DontNeedTo)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'untie/man,bagdad')
				(if (tunisia bagBound?)
					(Print 84 14)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'throw/canister')
				(Print 84 15)
			)
			((Said 'heist,get/man,money[/man<from]')
				(if (tunisia bagBound?)
					(Print 84 16)
				else
					(event claimed: FALSE)
				)
			)
		)
	)
	
	(method (newRoom nRoom)
		(cond 
			((== (ego script?) stayHereScript)
				(return)
			)
			(
				(and
					(or (& (tunisia flags?) $0001) (== tunisiaBagTimer 0))
					(== nRoom 80)
				)
				(ego setScript: stayHereScript)
			)
			(else (globalSound fade:)
				(super newRoom: nRoom &rest)
			)
		)
	)
)

(instance tossEverythingScript of Script

	(method (doit)
		(super doit:)
		(if (and (== state 0) (== (tunisia fridgeIs?) 0))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tunisia flags: (| (tunisia flags?) $0001))
				(if (or (ego has: 9) (ego has: 10) (ego has: 11))
					(localproc_003c 0)
				)
				(if (fridge script?) ((ScriptID 362 0) cue:))
			)
			(1
				(self setScript: (ScriptID 365 0) self)
			)
			(2 (= cycles 2))
			(3
				((ScriptID 365 1) dispose:)
				(DisposeScript 365)
				(Animate (cast elements?) FALSE)
				(if (& (tunisia flags?) $0004)
					(self setScript: (ScriptID 319 0))
				else
					(self setScript: (ScriptID 369 0))
					(= local22 1)
				)
			)
			(4
				(DisposeScript 369)
				(= local22 0)
				(= tunisiaBagTimer 10000)
				(tunisia flags: (& (tunisia flags?) $fffe))
				(ego ignoreControl: 16384)
				(self dispose:)
			)
		)
	)
)

(instance stayHereScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and local22 (localproc_000c (ScriptID 310 3)))
					(Print 84 17)
				else
					(Print 84 18)
				)
				(ego
					setMotion: stayMoveTo (ego x?) (- (ego y?) 10) self
				)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance stayMoveTo of MoveTo
	
	(method (dispose)
		(if (and (IsObject caller) (caller client?))
			(caller cue:)
		)
		(super dispose:)
	)
)

(instance phoneScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 984
					loop: (if (== (ego view?) 84) 1 else 2)
					cel: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(phone cel: 1 forceUpd:)
				(ego setCycle: EndLoop self)
			)
			(2
				(Print 84 19 #edit @str 20)
				(= seconds 3)
			)
			(3
				(cond 
					((not (StrCmp @str {})) (self cue:))
					((not (StrCmp @str {13-555-8097}))
						(if (not (& register $0001))
							(Print 84 20 #time 10)
							(Print 84 21)
							(User canInput: TRUE)
							(= seconds 10)
						else
							(Print 84 22)
							(ego setScript: putDownPhoneScript)
						)
					)
					((not (StrCmp @str {03-120-1204}))
						(if (not (& register $0002))
							(Print 84 23 #time 10)
							(User canInput: TRUE)
							(= seconds 10)
						else
							(Print 84 24)
							(ego setScript: putDownPhoneScript)
						)
					)
					(else
						(Print 84 25 #time 10)
						(ego setScript: putDownPhoneScript)
					)
				)
			)
			(4
				(Print 84 26)
				(ego setScript: putDownPhoneScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((not (StrCmp @str {03-120-1204}))
				(if (Said 'address[/man,guard,basal]')
					(|= register $0002)
					(tunisia madeCall: TRUE)
					(Print 84 27)
					(theGame changeScore: 2)
				else
					(Print 84 28)
					(event claimed: TRUE)
				)
				(Print 84 26)
				(ego setScript: putDownPhoneScript)
			)
			((not (StrCmp @str {13-555-8097}))
				(if
					(or
						(Said 'address[/man,bagdad,bagdad]')
						(Said 'order/food')
					)
					(|= register $0001)
					(Print 84 29)
					(Print 84 30)
					(= tunisiaBagTimer 200)
					(tunisia flags: (| (tunisia flags?) $0001))
					(theGame changeScore: 2)
				else
					(Print 84 28)
					(event claimed: TRUE)
				)
				(Print 84 26)
				(ego setScript: putDownPhoneScript)
			)
		)
	)
)

(instance putDownPhoneScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: CycleTo 3 -1 self))
			(1
				(phone cel: 0 forceUpd:)
				(ego setCycle: BegLoop self)
			)
			(2
				(ego
					view: (if (== (ego loop?) 1) 84 else 684)
					loop: 0
					setLoop: -1
					setCycle: Walk
					setScript: 0
				)
				(phone stopUpd:)
				(HandsOn)
			)
		)
	)
)

(instance windowski of RFeature
	(properties
		y 56
		x 159
		heading 180
		nsTop 40
		nsLeft 135
		nsBottom 67
		nsRight 183
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 31)
					)
					((GoToIfSaid self event 160 96 0 84 32))
					((Said 'look[<out,through]')
						(if (& (tunisia flags?) $0001)
							(Print 84 33)
						else
							(tunisia bagTimer: tunisiaBagTimer)
							(curRoom newRoom: 85)
						)
					)
				)
			)
		)
	)
)

(instance kitchen of RFeature
	(properties
		y 80
		x 285
		heading 225
		nsTop 64
		nsLeft 251
		nsBottom 97
		nsRight 319
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/kitchen]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 34)
						(Print 84 35)
					)
				)
			)
			((Said '/drawer>')
				(if (Said 'open,examine,look[<at,in]')
					(Print 84 36)
				)
			)
			((Said '/stove>')
				(cond 
					((Said 'open,examine,look[<at,in]')
						(Print 84 36)
					)
					((Said 'use,turn<on,off')
						(DontNeedTo)
					)
				)
			)
		)
	)
)

(instance sink of RFeature
	(properties
		y 82
		x 260
		heading 270
		nsTop 76
		nsLeft 254
		nsBottom 88
		nsRight 266
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/sink]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 37)
					)
					((Said 'look<below')
						(SeeNothing)
					)
					((Said 'use')
						(DontNeedTo)
					)
				)
			)
			(
				(or
					(Said 'drink,use,turn<on/water')
					(Said 'wash/hand,feet')
				)
				(DontNeedTo)
			)
		)
	)
)

(instance cabinet of RFeature
	(properties
		y 54
		x 226
		heading 180
		nsTop 40
		nsLeft 209
		nsBottom 68
		nsRight 244
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/cabinet]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 38)
					)
					((Said 'open,(look<in)')
						(Print 84 39)
					)
				)
			)
		)
	)
)

(instance kounter of RFeature
	(properties
		y 131
		x 244
		heading 180
		nsTop 98
		nsLeft 223
		nsBottom 131
		nsRight 266
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/counter]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at,on]')
						(Print 84 40)
					)
				)
			)
		)
	)
)

(instance fridge of Prop
	(properties
		y 142
		x 301
		heading 270
		view 284
		priority 9
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said '[/freezer,(box<ice)]>')
					(Said '/top/freezer,(box<ice)>')
				)
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 41)
					)
					((Said 'examine,look<in')
						(if (not cel)
							(Print 84 42)
						else
							(Print 84 43)
							(Print 84 44)
							(if (== (tunisia cheezIs?) 2)
								(Print 84 45)
							else
								(Print 84 46)
							)
							(Print 84 47)
						)
					)
					((Said 'close')
						(if (!= cel (self lastCel:))
							(Print 84 48)
						else
							(self setScript: (ScriptID 362 1))
						)
					)
					((Said 'look/top/freezer,(box<ice)')
						(if (ego has: iDuctTape)
							(SeeNothing)
						else
							(Print 84 49)
						)
					)
					(
						(and
							(or (tunisia bagBound?) (& (tunisia flags?) $0001))
							(Said 'open')
						)
						(Print 84 50)
					)
					((and local21 (Said 'open'))
						(Print 84 51)
					)
					((and cel (Said 'open'))
						(Print 84 52)
					)
					((GoToIfSaid self event self 12 'open' 84 32))
					((Said 'open')
						(HandsOff)
						(DisposeScript 363)
						(self setScript: (ScriptID 362 0))
					)
				)
			)
		)
	)
)

(instance fridgeSide of PicView
	(properties
		y 142
		x 306
		view 284
		loop 3
		priority 10
		signal ignrAct
	)
)

(instance sugar of Prop
	(properties
		y 128
		x 239
		z 24
		heading 180
		view 284
		loop 1
		priority 9
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said '[/canister<sugar]>')
					(Said '[/canister<big]>')
				)
				(cond 
					((Said 'drop,adjust,replace')
						(localproc_003c 0)
					)
					((and local21 (Said '(get[<!*])/*'))
						(Print 84 53)
					)
					(local21)
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 84 54))
					(
						(and
							(or (& (tunisia flags?) $0001) (tunisia bagBound?))
							(Said '/*')
						)
						(Print 84 55)
					)
					((or (Said 'open') (Said 'empty,pour[<out]'))
						(Print 84 56)
					)
					((GoToIfSaid self event self 15 '(get[<!*])/*' 84 32))
					((Said '(get[<!*])/*')
						(Print 84 57)
						(if (not (ego has: iTranquilizerGun))
							(Print 84 58)
						)
						(localproc_003c self)
					)
				)
			)
		)
	)
	
	(method (cue)
		(localproc_003c 0)
	)
)

(instance flour of Prop
	(properties
		y 128
		x 247
		z 24
		heading 180
		view 284
		loop 1
		cel 1
		priority 9
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said '[/canister<flour]>')
					(Said '[/canister<medium]>')
				)
				(cond 
					((Said 'drop,adjust,replace')
						(localproc_003c 0)
					)
					((and local21 (Said '(get[<!*])/*'))
						(Print 84 53)
					)
					(local21)
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 54)
					)
					(
						(and
							(or (& (tunisia flags?) $0001) (tunisia bagBound?))
							(Said '/*')
						)
						(Print 84 55)
					)
					((or (Said 'open') (Said 'empty,pour[<out]'))
						(Print 84 56)
					)
					((GoToIfSaid self event self 15 '(get[<!*])/*' 84 32))
					((Said '(get[<!*])/*')
						(Print 84 59)
						(localproc_003c self)
					)
				)
			)
		)
	)
	
	(method (cue)
		(localproc_003c 0)
	)
)

(instance coffee of Prop
	(properties
		y 128
		x 255
		z 24
		heading 180
		view 284
		loop 1
		cel 2
		priority 9
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said '[/canister<coffee]>')
					(Said '[/canister<little]>')
				)
				(cond 
					((Said 'drop,adjust,replace')
						(localproc_003c 0)
					)
					((and local21 (Said '(get[<!*])/*'))
						(Print 84 53)
					)
					(local21)
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 54)
					)
					(
						(and
							(or (& (tunisia flags?) $0001) (tunisia bagBound?))
							(Said '/*')
						)
						(Print 84 55)
					)
					((or (Said 'open') (Said 'empty,pour[<out]'))
						(Print 84 56)
					)
					((GoToIfSaid self event self 15 '(get[<!*])/*' 84 32))
					((Said '(get[<!*])/*')
						(Print 84 60)
						(localproc_003c self)
					)
				)
			)
		)
	)
	
	(method (cue)
		(localproc_003c 0)
	)
)

(instance phone of Prop
	(properties
		y 59
		x 198
		view 984
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/call]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 61)
					)
					((Said 'shoot')
						(Print 84 62)
					)
					(
						(and
							(& (tunisia flags?) $0001)
							(or
								(Said '(get[<!*]),dial,(pick<up)')
								(Said '(call<use)')
								(Said 'make/call<call')
								(Said 'call/guard,compound,bagdad')
							)
						)
						(Print 84 63)
					)
					(
						(or
							(Said '(get[<!*]),call,dial,(pick<up)')
							(Said '(call<use)')
							(Said 'make/call<call')
						)
						(if (User controls?)
							(HandsOff)
							(ego setAvoider: Avoider setMotion: MoveTo 187 95 self)
						else
							(Print 84 32)
						)
					)
				)
			)
		)
	)
	
	(method (cue)
		(ego setScript: phoneScript)
	)
)

(instance card of View
	(properties
		y 57
		x 192
		view 284
		loop 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/card]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 64)
					)
					((GoToIfSaid self event 187 95 0 84 32))
					((Said '(get[<!*])/*')
						(if (ego has: iBusinessCard)
							(AlreadyTook)
						else
							(Print 84 65)
							(ego get: iBusinessCard)
							(self dispose:)
						)
					)
				)
			)
		)
	)
)

(instance tape of View
	(properties
		y 97
		x 301
		view 284
		loop 4
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/tape]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 66)
					)
					((GoToIfSaid self event fridge 12 0 84 32))
					((Said '(get[<!*])/*')
						(Print 84 67)
						(ego get: iDuctTape)
						(theGame changeScore: 1)
						(self dispose:)
					)
				)
			)
		)
	)
)

(instance bed of RFeature
	(properties
		y 106
		x 87
		nsTop 89
		nsLeft 67
		nsBottom 123
		nsRight 106
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/bed]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 68)
					)
					((Said 'look<below,on')
						(SeeNothing)
					)
					((Said 'sit')
						(Print 84 69)
					)
					((Said 'rest')
						(Print 84 70)
					)
					((Said 'make')
						(DontNeedTo)
					)
				)
			)
		)
	)
)

(instance northWall of RFeature
	(properties
		y 59
		x 161
		nsTop 31
		nsLeft 73
		nsBottom 87
		nsRight 249
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/wall]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 71)
						(if (not (ego has: iBusinessCard))
							(Print 84 72)
						)
					)
				)
			)
		)
	)
)

(instance door of RFeature
	(properties
		y 167
		x 112
		nsTop 145
		nsLeft 83
		nsBottom 189
		nsRight 141
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 84 73)
					)
				)
			)
		)
	)
)
