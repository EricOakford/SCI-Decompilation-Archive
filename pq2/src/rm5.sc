;;; Sierra Script 1.0 - (do not remove this comment)
(script# 5)
(include system.sh)
(include keys.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm5 0
)
(synonyms
	(flyer cheeks painting)
)

(local
	lockerDoor
	gun
	ammoClips
	handcuffs
	stallDoor
	faucetOn
	inStall
	egoSitting
	[lockerCombination 3]
)
(procedure (LocPrint)
	(Print &rest #at -1 130)
)

(instance rm5 of Room
	(properties
		picture 5
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load VIEW 1)
		(Load VIEW 0)
		(Load VIEW 67)
		(self setLocales: regFieldKit)
		(= gunFireState gunPROHIBITED)
		(HandsOn)
		(= faucetOn 0)
		(= inStall 0)
		(self setScript: rm5Script)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(if (Said 'look>')
					(cond 
						((Said '[<at,around][/(!*,chamber,bathroom)]')
							(LocPrint 5 0 70 220)
						)
						((Said '[<at,up][/ceiling]')
							(LocPrint 5 1)
						)
						((Said '[<at,down][/floor]')
							(LocPrint 5 2)
						)
					)
				)
			)
		)
	)
)

(instance rm5Script of Script
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(<= 120 (ego x?))
					(<= (ego x?) 160)
					(<= 150 (ego y?))
					(<= (ego y?) 155)
				)
				(curRoom newRoom: 2)
			)
			((<= (ego y?) 126)
				(if (!= (mod (ego view?) 2) 0)
					(ego view: (- (ego view?) 1))
				)
			)
			((!= (mod (ego view?) 2) 1)
				(ego view: (+ (ego view?) 1))
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User prevDir: 1)
				(ego
					view: (if (not gunDrawn) 1 else 7)
					posn: 141 148
					setPri: -1
					init:
					setMotion: MoveTo 141 140
					setCycle: Walk
					illegalBits: cWHITE ;-32768
				)
				((= lockerDoor (Prop new:))
					view: 67
					posn: 84 115
					cel: 0
					init:
					stopUpd:
				)
				((View new:)
					view: 64
					loop: 0
					cel: 1
					posn: 178 110
					setPri: 7
					init:
					addToPic:
				)
				((View new:)
					view: 64
					loop: 0
					cel: 0
					posn: 168 129
					setPri: 9
					init:
					ignoreActors:
				)
				((View new:)
					view: 64
					loop: 2
					cel: 0
					posn: 239 127
					setPri: 7
					init:
					ignoreActors: 0
				)
				((View new:)
					view: 64
					loop: 2
					cel: 0
					posn: 259 132
					setPri: 7
					init:
					ignoreActors: 0
				)
				((= stallDoor (Prop new:))
					view: 64
					loop: 1
					cel: 0
					posn: 216 124
					setPri: 7
					init:
					ignoreActors:
					stopUpd:
				)
			)
			(1
				(lockerDoor
					startUpd:
					cycleSpeed: 2
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(curRoom setScript: lockerScript)
			)
			(3
				(ego
					posn: 94 118
					view: 0
					loop: 1
					cel: 6
					setCycle: Walk
					init:
				)
				(lockerDoor
					view: 67
					posn: 84 115
					startUpd:
					cycleSpeed: 2
					cel: 4
					setCycle: BegLoop
					init:
				)
				(curRoom drawPic: 5)
			)
			(4
				(HandsOff)
				(stallDoor setCycle: EndLoop self)
			)
			(5
				(stallDoor stopUpd:)
				(ego
					setPri: 6
					illegalBits: 0
					setMotion: MoveTo 210 120 self
				)
			)
			(6
				(ego setMotion: MoveTo 221 123 self)
			)
			(7
				(User canInput: 1)
				(stallDoor setCycle: BegLoop)
			)
			(8
				(switch (Random 0 2)
					(0
						(LocPrint 5 3)
					)
					(1
						(LocPrint 5 4)
					)
					(2
						(LocPrint 5 5)
					)
				)
			)
			(9
				(HandsOff)
				(= egoSitting 0)
				(stallDoor setCycle: EndLoop self)
			)
			(10
				(ego setMotion: MoveTo 210 120 self)
			)
			(11
				(ego setPri: -1 setMotion: MoveTo 195 123 self)
			)
			(12
				(HandsOn)
				(ego illegalBits: cWHITE) ;-32768
				(stallDoor setCycle: BegLoop self)
			)
			(13
				(stallDoor stopUpd:)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
			(and
				(== (event type?) keyDown)
				inStall
				(or
					(== (event message?) KEY_F6)
					(== (event message?) KEY_F8)
					(== (event message?) KEY_F10) ;14848
				)
			)
			(event claimed: 1)
			(LocPrint 5 6)
		)
		(if
		(or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(cond 
			((Said 'pick/lock')
				(LocPrint 5 7)
			)
			((Said 'stand')
				(if (not egoSitting)
					(LocPrint 5 8)
				else
					(LocPrint 5 9)
					(= egoSitting 0)
				)
			)
			((Said 'sat')
				(cond 
					(inStall
						(ego loop: 1)
						(LocPrint 5 9 83)
						(= egoSitting 1)
					)
					((ego inRect: 77 100 195 135)
						(LocPrint 5 10)
					)
					(else
						(LocPrint 5 11)
					)
				)
			)
			((Said 'get/crud')
				(if (ego inRect: 77 100 195 135)
					(LocPrint 5 12)
				else
					(LocPrint 5 11)
				)
			)
			((Said 'move/bench')
				(if (ego inRect: 77 100 195 135)
					(LocPrint 5 13)
				else
					(LocPrint 5 11)
				)
			)
			((Said 'climb/bench')
				(if (ego inRect: 77 100 195 135)
					(LocPrint 5 14)
				else
					(NotClose)
				)
			)
			(
				(and
					(not (& (ego onControl: 1) $00e0)) ;??
					(Said 'look,read/locker,door,label,name')
				)
				(switch (ego onControl: 1)
					(cGREEN
						(LocPrint 5 15)
					)
					(cLMAGENTA
						(LocPrint 5 16)
					)
					(cLRED
						(LocPrint 5 17)
					)
					(cLCYAN
						(LocPrint 5 18)
					)
					(cLGREEN
						(LocPrint 5 19)
					)
					(cLBLUE
						(LocPrint 5 20)
					)
					(cGREY
						(LocPrint 5 21)
					)
					(cCYAN
						(LocPrint 5 22)
					)
					(cRED
						(LocPrint 5 23)
					)
					(else
						(LocPrint 5 24)
					)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/crud')
						(LocPrint 5 25)
					)
					((Said '/pane')
						(LocPrint 5 26)
					)
					((Said '<below/bench')
						(LocPrint 5 27)
					)
					((Said '/bench')
						(LocPrint 5 28)
					)
					((Said '<below/door,stall')
						(cond 
							((& (ego onControl: 1) cMAGENTA) ;$0020
								(switch (Random 0 2)
									(0
										(LocPrint 5 29)
									)
									(1
										(LocPrint 5 30)
									)
									(2
										(LocPrint 5 31)
									)
								)
							)
							((& (ego onControl: 1) cLGREY) ;$00c0
								(LocPrint 5 32)
							)
							(else (LocPrint 5 33))
						)
					)
					((Said '/crapper,stall')
						(if inStall
							(LocPrint 5 34)
						else
							(LocPrint 5 35)
						)
					)
					(
						(and 
							(& (ego onControl: 1) cLGREY) ;$00e0
							(Said '/door')
						)
						(LocPrint 5 36)
					)
					((Said '/lock,combination')
						(if (& (ego onControl: 1) cGREEN)
							(LocPrint 5 37)
						else
							(LocPrint 5 38)
						)
					)
					((Said '/mirror')
						(if (== (ego onControl: 1) cYELLOW)
							(LocPrint 5 39)
						else
							(NotClose)
						)
					)
					((Said '/basin[<bath]')
						(if (== (ego onControl: 1) cYELLOW)
							(LocPrint 5 40)
						else
							(NotClose)
						)
					)
				)
			)
			((Said 'crawl<below/door,stall')
				(if (& (ego onControl: 1) cLGREY)
					(LocPrint 5 41)
				else
					(NotClose)
				)
			)
			((Said 'chat/dude,cop')
				(if
					(or
						inStall
						;(& (ego onControl: 1) $0120)
						(== (ego onControl: 1) cMAGENTA)
					)
					(switch (Random 0 3)
						(0
							(LocPrint 5 42)
						)
						(1
							(LocPrint 5 43)
						)
						(2
							(LocPrint 5 44)
						)
						(3
							(LocPrint 5 45)
						)
					)
				else
					(NotClose)
				)
			)
			((Said 'knock')
				(cond 
					((== (ego onControl: 1) cMAGENTA)
						(switch (Random 0 4)
							(0
								(LocPrint 5 46)
							)
							(1
								(LocPrint 5 47)
							)
							(2
								(LocPrint 5 48)
							)
							(3
								(LocPrint 5 49)
							)
							(4
								(LocPrint 5 50)
							)
						)
					)
					((& (ego onControl: 1) cLGREY) ;was $00e0
						(LocPrint 5 51)
					)
					(else
						(LocPrint 5 52)
					)
				)
			)
			(
				(and
					(not (& (ego onControl: 1) cLGREY)) ;$00e0
					(Said 'unlock,open/lock,locker,door')
				)
				(if (!= (ego onControl: 1) cGREEN) ;4
					(LocPrint 5 53)
				else
					(= [lockerCombination 0]
						(GetNumber {First # of your combination?})
					)
					(= [lockerCombination 1]
						(GetNumber {Second # of your combination?})
					)
					(= [lockerCombination 2]
						(GetNumber {Third # of your combination?})
					)
					(if
						(and
							(== [lockerCombination 0] 36)
							(== [lockerCombination 1] 4)
							(== [lockerCombination 2] 12)
						)
						(self changeState: 1)
						(SolvePuzzle 5 fOpenLocker)
					else
						(switch (Random 0 2)
							(0
								(LocPrint 5 54)
							)
							(1
								(LocPrint 5 55)
							)
							(2
								(LocPrint 5 56)
							)
						)
					)
				)
			)
			(
				(or
					(Said 'open/door,stall')
					(and
						(not inStall)
						(Said 'enter/bathroom,stall,crapper')
					)
				)
				(cond 
					((& (ego onControl: 1) cBROWN) ;$0040
						(LocPrint 5 57)
					)
					((& (ego onControl: 1) cMAGENTA) ;$0020
						(LocPrint 5 58)
					)
					((not (& (ego onControl: 1) cLGREY)) ;$00e0
						(LocPrint 5 52)
					)
					(inStall
						(= inStall 0)
						(rm5Script changeState: 9)
					)
					(else
						(= inStall 1)
						(rm5Script changeState: 4)
					)
				)
			)
			((Said 'exit,(get<out)[/stall,crapper]')
				(if inStall
					(= inStall 0)
					(rm5Script changeState: 9)
				else
					(event claimed: 0)
				)
			)
			((Said 'open/pane')
				(LocPrint 5 59)
			)
			(
				(or
					(Said 'use,go/crapper,bathroom')
					(Said 'crap,leak,crap,crap')
					(Said 'pull<down/jeans')
				)
				(if inStall
					(rm5Script changeState: 8)
				else
					(LocPrint 5 60)
				)
			)
			(
				(or
					(Said 'wipe/ass')
					(Said 'shake/cock')
					(Said 'flush/crapper')
				)
				(if inStall
					(LocPrint 5 61)
				else
					(LocPrint 5 60)
				)
			)
			(
				(or
					(Said 'use/basin')
					(Said 'bath/hand,face')
				)
				(if (& (ego onControl: 1) cYELLOW) ;$4000
					(LocPrint 5 62)
				else
					(LocPrint 5 63)
				)
			)
			((Said 'dry/hand')
				(LocPrint 5 59)
			)
			((Said 'turn<on/water,faucet')
				(if (== (ego onControl: 1) cYELLOW) ;16384
					(if (not faucetOn)
						(= faucetOn 1)
						(LocPrint 5 64)
					else
						(LocPrint 5 65)
					)
				else
					(LocPrint 5 66)
				)
			)
			((Said 'turn<off/water,faucet')
				(if (== (ego onControl: 1) cYELLOW) ;16384
					(if faucetOn
						(= faucetOn 0)
						(LocPrint 5 67)
					else
						(NotClose)
					)
				else
					(LocPrint 5 66)
				)
			)
			((Said 'drink/water')
				(LocPrint 5 68)
			)
			(
				(or
					(Said 'close[/locker,door]')
					(Said 'lock[/locker,door]')
				)
				(if (== (ego onControl: 1) cGREEN) ;4
					(LocPrint 5 69)
				else
					(LocPrint 5 70)
				)
			)
			((Said 'find/locker')
				(LocPrint 5 71)
			)
		)
	)
)

(instance lockerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Load VIEW 69)
				(Load VIEW 64)
				(Load PICTURE 9)
				(User canInput: 1)
				(curRoom drawPic: 9)
				(cast eachElementDo: #dispose)
				((= gun (Actor new:))
					view: 69
					posn: 146 189
					setPri: 13
					loop: 0
					cel: 0
					init:
					stopUpd:
				)
				(if (not (InRoom iHandGun))
					(gun posn: 146 1189)
				)
				((= ammoClips (Actor new:))
					view: 69
					posn: 227 41
					setPri: 12
					loop: 5
					cel:
						(switch bulletsInGun
							(0
								(+
									2
									(> [numAmmoClips 1] 0)
									(> [numAmmoClips 2] 0)
								)
							)
							(1
								(> [numAmmoClips 2] 0)
							)
							(else 
								(> [numAmmoClips 1] 0)
							)
						)
					init:
					stopUpd:
				)
				(if (not (InRoom iAmmoClips))
					(ammoClips posn: 227 1041)
				)
				((= handcuffs (Actor new:))
					view: 69
					posn: 216 136
					setPri: 13
					loop: 0
					cel: 1
					init:
					stopUpd:
				)
				(if (not (InRoom iHandcuffs))
					(handcuffs posn: 216 1136)
				)
				((View new:)
					view: 69
					posn: 219 102
					setPri: 12
					loop: 2
					cel: 0
					init:
					stopUpd:
				)
				((View new:)
					view: 69
					posn: 100 102
					setPri: 12
					loop: 3
					cel: 0
					init:
					stopUpd:
				)
				((View new:)
					view: 69
					posn: 156 76
					setPri: 12
					loop: 1
					cel: 0
					init:
					stopUpd:
				)
				((View new:)
					view: 69
					posn: 50 156
					setPri: 12
					loop: 4
					cel: 0
					init:
					stopUpd:
				)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if
			(and
				(== (event type?) keyDown)
				(or
					(== (event message?) KEY_F6)
					(== (event message?) KEY_F8)
					(== (event message?) KEY_F10) ;was 14848??
				)
			)
			(event claimed: 1)
			(LocPrint 5 6)
		)
		(if
			(or
				(event claimed?)
				(!= (event type?) saidEvent)
			)
			(return)
		)
		(cond 
			((Said '(replace,remove,deposit)>')
				(cond 
					((Said '/gunbelt,9mm')
						(if (not (ego has: iHandGun))
							(LocPrint 5 72)
						else
							(LocPrint 5 73)
							(gun posn: 146 189)
							(PutInRoom iHandGun)
						)
					)
					((Said '/arrest')
						(if (not (ego has: iHandcuffs))
							(LocPrint 5 72)
						else
							(LocPrint 5 74)
							(handcuffs posn: 216 136)
							(PutInRoom iHandcuffs)
						)
					)
					((Said '/bullet,ammo,(clip[<ammo])')
						(if (not (ego has: iAmmoClips))
							(LocPrint 5 72)
						else
							(LocPrint 5 75)
							(ammoClips
								cel:
									(switch bulletsInGun
										(0
											(+
												2
												(> [numAmmoClips 1] 0)
												(> [numAmmoClips 2] 0)
											)
										)
										(1
											(> [numAmmoClips 2] 0)
										)
										(else
											(> [numAmmoClips 1] 0)
										)
									)
								posn: 227 41
							)
							(PutInRoom iAmmoClips)
						)
					)
					((= temp0 (inventory saidMe: event))
						(if (ego has: (inventory indexOf: temp0))
							(Print 5 76)
						else
							(DontHave)
						)
					)
					(else
						(event claimed: 1)
						(CantDo)
					)
				)
			)
			((Said 'get,remove>')
				(cond 
					((Said '/gunbelt,9mm')
						(if (InRoom iHandGun)
							(LocPrint 5 77)
							(gun posn: 100 1000)
							(ego get: iHandGun)
							(SolvePuzzle 1 fGetGun)
						else
							(LocPrint 5 78)
						)
					)
					((Said '/bullet,ammo,(clip[<ammo])')
						(if (InRoom iAmmoClips)
							(LocPrint 5 79)
							(ego get: iAmmoClips)
							(SolvePuzzle 1 fGetAmmoClips)
							(ammoClips posn: 100 1000)
						else
							(LocPrint 5 80)
						)
					)
					((Said '/arrest')
						(if (InRoom iHandcuffs)
							(LocPrint 5 81)
							(ego get: iHandcuffs)
							(SolvePuzzle 1 fGetHandcuffs)
							(handcuffs posn: 100 1000)
						else
							(LocPrint 5 82)
						)
					)
					((Said '/flyer')
						(LocPrint 5 83)
					)
				)
			)
			((Said 'look>')
				(cond 
					((Said '[<at,around,in][/locker]')
						(inventory
							carrying: {Your locker contains:}
							empty: {Your locker is empty.}
							showSelf: 5
						)
					)
					((Said '/bookcase')
						(if (InRoom iAmmoClips)
							(LocPrint 5 84)
						else
							(LocPrint 5 85)
						)
					)
					((Said '/flyer')
						(LocPrint 5 86)
					)
					((Said '/bullet,ammo,(clip[<ammo])')
						(if (InRoom iAmmoClips)
							(LocPrint 5 87)
						else
							(event claimed: 0)
						)
					)
					((Said '/9mm,gunbelt')
						(if (InRoom iHandGun)
							(LocPrint 5 88)
						else
							(event claimed: 0)
						)
					)
					((Said '/arrest')
						(if (InRoom iHandcuffs)
							(LocPrint 5 89)
						else
							(event claimed: 0)
						)
					)
					(else
						(event claimed: 0)
					)
				)
			)
			((Said 'kiss/flyer,flyer,button')
				(LocPrint 5 90)
				(LocPrint 5 91)
			)
			(
				(or
					(Said 'lock,close[/locker,door]')
					(Said 'exit')
				)
				(curRoom drawPic: (curRoom picture?))
				(cast eachElementDo: #dispose)
				(HandsOn)
				(curRoom setScript: rm5Script)
				(rm5Script changeState: 3)
			)
		)
	)
)
