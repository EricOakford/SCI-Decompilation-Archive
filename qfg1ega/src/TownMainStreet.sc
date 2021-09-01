;;; Sierra Script 1.0 - (do not remove this comment)
(script# 310)
(include game.sh)
(use Main)
(use Door)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm310 0
)

(local
	oldLadyDoor
	magicDoor
	nearMagicDoor
	guildDoor
	magicSign
	eyeX
	eyeY
	local7
)
(procedure (EyeMoves theX theY)
	(if (or (!= eyeX theX) (!= eyeY theY))
		(= eyeX theX)
		(= eyeY theY)
		(magicSign posn: theX theY)
	)
)

(instance rm310 of Room
	(properties
		picture 310
		style WIPELEFT
	)
	
	(method (init)
		(if (and (>= timeODay TIME_SUNSET) [egoStats PICK])
			(Load SOUND (SoundFX 35))
		)
		(Load SOUND (SoundFX 46))
		(Load SOUND (SoundFX 28))
		(Load VIEW vTownMagicOutside)
		(Load VIEW vEgoTired)
		(super init:)
		(mouseDownHandler add: self)
		(StatusLine enable:)
		(self setLocales: STREET TOWN)
		(NormalEgo)
		(ego
			illegalBits: (if (> timeODay TIME_SUNSET) cWHITE else (| cWHITE cLCYAN))
			init:
		)
		(flameSound number: (SoundFX 46) init:)
		(teleport number: (SoundFX 28) init:)
		(= eyeX 252)
		(= eyeY 52)
		((= magicSign (Prop new:))
			view: vTownMagicOutside
			loop: 3
			posn: eyeX eyeY
			init:
			setPri: 10
		)
		((View new:)
			view: vTownMagicOutside
			loop: 4
			cel: 0
			posn: 252 25
			setPri: 6
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vTownMagicOutside
			loop: 7
			cel: 0
			posn: 35 43
			init:
			stopUpd:
			addToPic:
		)
		(flames init:)
		(if (< timeODay TIME_SUNSET)
			(LOL init: cycleSpeed: 10 setCycle: Forward startUpd:)
		)
		(= magicDoor (Prop new:))
		(magicDoor
			view: vTownMagicOutside
			loop: 0
			cel: (if (== prevRoomNum 314)
				(- (NumCels magicDoor) 1)
			else
				0
			)
			posn: 253 114
			init:
			ignoreActors:
			stopUpd:
			setPri: 8
			cycleSpeed: 2
		)
		((= oldLadyDoor (Door new:))
			view: vTownMagicOutside
			loop: 1
			posn: 137 38
			doorControl: cYELLOW
			entranceTo: 313
			locked: TRUE
			facingLoop: loopN
			setPri: 5
			init:
		)
		((= guildDoor (Door new:))
			view: vTownMagicOutside
			loop: 2
			posn: 6 148
			doorControl: cLRED
			entranceTo: 311
			facingLoop: loopW
			setPri: 9
			illegalBits: 0
			locked: (if Night TRUE else FALSE)
			init:
		)
		(switch prevRoomNum
			(0
				(ego posn: 318 177 setMotion: MoveTo 305 170)
			)
			(300
				(ego posn: 318 177 setMotion: MoveTo 305 170)
			)
			(311
				(ego posn: 35 141 loop: 0)
				(guildDoor close:)
			)
			(313
				(ego posn: 154 91)
				(oldLadyDoor close:)
			)
			(314
				(teleport play:)
				(ego posn: 252 119)
				(magicDoor
					setCel: (- (NumCels magicDoor) 1)
					setCycle: BegLoop
				)
			)
			(999
				(ego
					view: vEgoTired
					setLoop: 3
					cel: 5
					init:
					setScript: egoWakes
				)
			)
		)
	)
	
	(method (doit &tmp egoX egoY temp2 temp3)
		(super doit:)
		(cond 
			((> (ego x?) 318)
				(= exploringTown FALSE)
				(curRoom newRoom: 300)
			)
			(
				(and
					(== (ego onControl: origin) cLMAGENTA)
					(not nearMagicDoor)
					(== (ego loop?) 3)
				)
				(= nearMagicDoor TRUE)
				(magicDoor setScript: magicDoorScript)
			)
		)
		(= egoX (ego x?))
		(= egoY (ego y?))
		(= temp2 eyeX)
		(= temp3 eyeY)
		(cond 
			((<= egoY 125) (= temp3 54))
			((<= egoY 140) (= temp3 53))
			(else (= temp3 52))
		)
		(cond 
			((<= egoX 160) (= temp2 246) (= temp3 52))
			((<= egoX 200) (= temp2 248) (= temp3 52))
			((<= egoX 240) (= temp2 250))
			((<= egoX 270) (= temp2 252))
			((<= egoX 290) (= temp2 254))
			(else (= temp2 256))
		)
		(EyeMoves temp2 temp3)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn310)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'look/lol,female')
				(HighPrint 310 4)
				;She seems to have retired...or maybe she's at the tavern.
			)
			((Said 'look/hasp')
				(cond 
					((== (ego onControl: origin) cLMAGENTA)
						(HighPrint 310 5)
						;This (door?) doesn't have a visible lock.
					)
					((== (ego onControl: origin) cYELLOW)
						(HighPrint 310 6)
						;Not the sturdiest you've ever seen.
					)
					(else
						(NotClose)
					)
				)
			)
			((Said 'knock,open/door')
				(cond 
					(
						(and
							(== (ego onControl: origin) cLMAGENTA)
							(not nearMagicDoor)
							(!= (ego loop?) 3)
						)
						(HighPrint 310 7)
						;Turn around and go on in.
					)
					((== (ego onControl: origin) cYELLOW)
						(if (< timeODay TIME_SUNSET)
							(HighPrint 310 8)
							;The door appears to be locked. 
							; It also appears that the occupant is taking her nap at the bottom of the stairs right now.
						else
							(HighPrint 310 9)
							;No one seems to be home, and the door is securely locked.
						)
					)
					((== (ego onControl: origin) cLRED)
						(if Night
							(HighPrint 310 10)
							;The door appears to be locked.
						else
							(HighPrint 310 7)
							;Turn around and go on in.
						)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			(
			(Said 'look[<at,around][/!*,hamlet,street,building]')
			(HighPrint 310 11)
			;You have come to the end of the main street.
			; The town wall is to the south.
			; You have an eerie feeling that someone is watching you.
			)
			(
				(or
					(MouseClaimed magicDoor event shiftDown)
					(Said 'look/shop,shop[<magic]')
				)
				(HighPrint 310 12)
				;Not only does this building look weird, but it feels weird to be near it.
			)
			(
				(or
					(MouseClaimed oldLadyDoor event shiftDown)
					(Said 'look/house[<lol,female]')
				)
				(HighPrint 310 13)
				;The house looks closed up.  There is no sign of the Little Old Lady.
			)
			(
				(or
					(MouseClaimed guildDoor event shiftDown)
					(Said 'look/hall[<club]')
				)
				(if Night
					(HighPrint 310 14)
					;The Guild Hall building appears to be dark and closed up.
				else
					(HighPrint 310 15)
					;You can just see a thin thread of light under the door, so there is probably someone inside.
				)
			)
			((Said 'look/fence')
				(HighPrint 310 16)
				;The fence around the Little Old Lady's house looks like it's made of wrought iron.
			)
			(
				(or
					(MouseClaimed onEye event shiftDown)
					(Said 'look/eye')
				)
				(HighPrint 310 17)
				;There's no getting around it...a shop with its eye on you makes you nervous.
			)
			((MouseClaimed onMagicSign event shiftDown)
				(HighPrint 310 18)
				;It would seem that the strangely decorated building is involved in the magic trade.
			)
			((MouseClaimed onGuildSign event shiftDown)
				(HighPrint 310 19)
				;Judging from what the sign says, the building at the end of the street is the Guild Hall.
			)
			((Said 'look/sign')
				(if (> (ego x?) 200)
					(HighPrint 310 18)
					;It would seem that the strangely decorated building is involved in the magic trade.
				else
					(HighPrint 310 19)
					;Judging from what the sign says, the building at the end of the street is the Guild Hall.
				)
			)
			(
				(or
					(and
						(not (MouseClaimed onMagicSign event shiftDown))
						(MouseClaimed flames event shiftDown)
					)
					(Said 'look/flame')
				)
				(if (> (flameScript state?) 0)
					(HighPrint 310 20)
					;Isn't it hard to tear your eyes away? "PYROMANIA."
				else
					(HighPrint 310 21)
					;Flames? What flames?  You don't see any flames.
				)
			)
		)
	)
	
	(method (notify param1)
		(cond 
			((!= param1 1))
			((not (oldLadyDoor locked?))
				(HighPrint 310 0)
				;The door isn't locked.  Just turn around.
				)
			((not (TrySkill PICK 30 lockPickBonus))
				(HighPrint 310 1)
				;Picking locks looked a lot easier in the instruction book.
				)
			((and (Btst fBeenIn313) (< dayLOLBreakIn Day))
				(HighPrint 310 2)
				;The owners seem to have been made nervous by the recent rash of house burglaries.
				;The door is barred from the inside.  But you do get some valuable lock-picking practice.
			)
			(else
				(lockSound number: (SoundFX 35) init: play:)
				(HighPrint 310 3)
				;You hear a "Snick!".  The lock is open!
				(oldLadyDoor facingLoop: (ego loop?) locked: FALSE)
			)
		)
	)
)

(instance magicDoorScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(teleport play:)
				(magicDoor setCycle: EndLoop self)
			)
			(1
				(ego illegalBits: 0 setMotion: MoveTo 254 108 self)
			)
			(2
				(curRoom newRoom: 314)
			)
		)
	)
)

(instance flames of Prop
	(properties
		y 34
		x 253
		view vTownMagicOutside
		loop 5
		cycleSpeed 2
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: flameScript)
	)
)

(instance flameScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client stopUpd:)
				(= register (Random 1 3))
				(= cycles (Random 60 150))
			)
			(1
				(client setCycle: EndLoop self)
				(= cycles 3)
			)
			(2 (flameSound loop: 1 play:))
			(3
				(if (-- register)
					(self changeState: 1)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance onMagicSign of RFeature
	(properties
		nsTop 1
		nsLeft 216
		nsBottom 19
		nsRight 280
	)
)

(instance onGuildSign of RFeature
	(properties
		nsTop 22
		nsLeft 3
		nsBottom 38
		nsRight 71
	)
)

(instance onEye of RFeature
	(properties
		nsTop 44
		nsLeft 234
		nsBottom 59
		nsRight 269
	)
)

(instance lockSound of Sound
	(properties
		number 35
	)
)

(instance flameSound of Sound
	(properties
		priority 10
	)
)

(instance teleport of Sound
	(properties
		priority 12
	)
)

(instance egoWakes of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(1
				(NormalEgo)
				(HighPrint 310 22)
				(= [invNum iSilver] 0)
				(= [invNum iGold] 0)
				(HandsOn)
			)
		)
	)
)

(instance LOL of Prop
	(properties
		y 125
		x 167
		view vTownMagicOutside
		loop 6
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(MouseClaimed LOL event shiftDown)
					(Said 'look/lol,female')
				)
				(HighPrint 310 23)
				;She's deeply asleep in her rocker.
			)
			((Said 'look/rocker,chair')
				(HighPrint 310 24)
				;In it sits the Little Old Lady, asleep.
			)
			((Said 'awaken/lol,female')
				(HighPrint 310 25)
				;She's really sleeping soundly and doesn't notice your presence.
			)
			((Said 'ask,chat')
				(HighPrint 310 26)
				;"ZZZZZZZZZZZZZZZZZZZZ"!
				(HighPrint 310 27)
				;"Grmpf..snrt..hmphspft"!
				(HighPrint 310 26)
				;"ZZZZZZZZZZZZZZZZZZZZ"!
			)
			(
				(or
					(MouseClaimed oldLadyDoor event shiftDown)
					(Said 'look/house[<lol,female]')
				)
				(HighPrint 310 28)
				;This looks like a nice, neat little house. There is a grandmotherly little old lady rocking on the front lawn.
			)
		)
	)
)
