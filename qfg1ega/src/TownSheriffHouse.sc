;;; Sierra Script 1.0 - (do not remove this comment)
(script# 321)
(include game.sh)
(use Main)
(use LoadMany)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)

(public
	rm321 0
	vase 1
	miscMusic 2
	sneakMusic 3
	portrait 4
	sheriff 5
	otto 6
	bottomDoor 7
	leftDoor 8
	rightDoor 9
	musicBox 10
)

(local
	local0
	safeCrackSuccess	;this should be a global variable
	local2
	egoUpstairs
	vaseOutOfWay
	safeRevealed
	safeOpen
	local7
)
(procedure (BreakInPrint)
	(if egoUpstairs
		(CenterPrint &rest)
	else
		(HighPrint &rest)
	)
)

(procedure (SearchDrawer)
	(Bset fSearchedDrawer)
	(ego get: iSilver 3)
	(BreakInPrint 321 0)
	;In the desk drawers, you find an assortment of mostly worthless objects, but you find three silvers, which you take.
	(SolvePuzzle f321SearchDrawer 1 THIEF)
)

(instance otto of Actor)

(instance sheriff of Actor)

(instance vase of Actor)

(instance leftDoor of Prop)

(instance rightDoor of Prop)

(instance bottomDoor of Prop)

(instance safeDoor of View)

(instance portrait of View)

(instance candelabra of View)

(instance chair of View)

(instance musicBox of View)

(instance rm321 of Room
	(properties
		picture 321
		style WIPERIGHT
	)
	
	(method (init)
		(LoadMany SCRIPT REVERSE 288 289 290)
		(LoadMany TEXT 288 289 290)
		(LoadMany VIEW vSheriffHouse vOttoAsleep vSheriffAsleep vEgoBigGrin vEgoDeadBrigands vEgoDefeated)
		(LoadMany SOUND 8 13
			(SoundFX 52)
			(SoundFX 14)
			(SoundFX 15)
			(SoundFX 35)
		)
		(super init:)
		(SolvePuzzle f321EnterSheriffHouse 5 THIEF)
		(mouseDownHandler add: self)
		(self
			setFeatures:
				onDesk
				onCandles
				onVase
				onMusicBox
				onFireplace
				onPaintingDown
				onPaintingUp
				onSafe
				onPlant
				onLeftDoor
				onRightDoor
				onBottomDoor
				onChair
				onCouch
				onStuffedChair
				onTable
		)
		(self setLocales: TOWN)
		(StatusLine enable:)
		(sneakMusic init:)
		(miscMusic init:)
		(= deathMusic (SoundFX 52))
		(if (Btst fCrackedSafe)
			(= safeOpen TRUE)
		)
		(if (Btst fUncoveredSafe)
			(= safeRevealed TRUE)
		)
		(if (Btst fStoleVase)
			(= vaseOutOfWay TRUE)
		)
		(NormalEgo)
		(ego
			posn: 163 188
			illegalBits: (| cWHITE cLCYAN)
			init:
			setMotion: MoveTo 163 169
		)
		(leftDoor
			view: vSheriffHouse
			loop: 1
			cel: 0
			posn: 129 55
			ignoreActors:
			init:
			stopUpd:
		)
		(rightDoor
			view: vSheriffHouse
			loop: 2
			cel: 0
			posn: 153 55
			ignoreActors:
			init:
			stopUpd:
		)
		(bottomDoor
			view: vSheriffHouse
			loop: 3
			cel: 0
			posn: 109 119
			ignoreActors:
			init:
			stopUpd:
		)
		(otto
			view: vOttoAsleep
			illegalBits: 0
			ignoreActors:
			init:
			setCycle: Walk
			stopUpd:
		)
		(sheriff
			view: vSheriffAsleep
			illegalBits: 0
			ignoreActors:
			init:
			setCycle: Walk
			stopUpd:
		)
		(safeDoor
			view: vSheriffHouse
			loop: 0
			cel: (if (Btst fCrackedSafe) 1 else 0)
			posn: 271 106
			init:
			stopUpd:
		)
		(if (not (Btst fStoleVase))
			(vase
				view: vSheriffHouse
				loop: 5
				cel: 0
				illegalBits: 0
				ignoreActors:
				posn: (if (Btst fMovedVase) 247 else 262) (if (Btst fMovedVase) 136 else 119)
				setPri: 10
				init:
				stopUpd:
			)
		)
		(portrait
			view: vSheriffHouse
			loop: 4
			cel: 0
			posn: 277 (if (not (Btst fUncoveredSafe)) 121 else 109)
			init:
			stopUpd:
		)
		(if (not (Btst fStoleCandelabra))
			(candelabra
				view: vSheriffHouse
				loop: 4
				cel: 1
				posn: 172 88
				setPri: 9
				init:
				stopUpd:
			)
		)
		(chair
			view: vSheriffHouse
			loop: 4
			cel: 2
			posn: 290 161
			init:
			stopUpd:
			addToPic:
		)
		(if (not (Btst fStoleMusicBox))
			(musicBox
				view: vSheriffHouse
				loop: 4
				cel: 3
				posn: 138 145
				setPri: 12
				init:
				stopUpd:
			)
		)
		(sneakMusic play:)
		(self setScript: (ScriptID 288 3))
	)
	
	(method (doit)
		(if (and (== (ego edgeHit?) SOUTH) (not (Btst fOttoAwakened)))
			(= daySheriffBreakIn Day)
			(curRoom newRoom: 320)
		)
		(cond 
			(
				(and
					(> (ego x?) 35)
					(< (ego y?) 92)
					(== (ego loop?) 0)
					(not egoUpstairs)
					(not (Btst fWokeUpSheriff))
				)
				(= egoUpstairs TRUE)
				(ego setScript: (ScriptID 288 1))
			)
			(
				(and
					egoUpstairs
					(< (ego x?) 87)
					(< (ego y?) 62)
					(== (ego loop?) 1)
					(not (Btst fWokeUpSheriff))
				)
				(= egoUpstairs FALSE)
				(ego setScript: (ScriptID 288 2))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bclr fOttoAwakened)
		(Bset fBeenIn321)
		(= deathMusic (SoundFX 26))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'search,open,(look<in)/desk,drawer')
						(cond 
							((Btst fSearchedDrawer)
								(BreakInPrint 321 1)
								;You've looked in there already.
							)
							((ego inRect: 167 127 196 132)
								(SearchDrawer)
							)
							(else
								(NotClose)
							)
						)
					)
					((Said 'look,search>')
						(cond 
							((Said '[<at,around][/!*,room,house]')
								(cond 
									((Btst fBeenIn321)
										(BreakInPrint 321 2)
										;The room is dark and still.
									)
									(
										(or
											(not (Btst fStoleCandelabra))
											(not (Btst fStoleVase))
											(not (Btst fStoleMusicBox))
										)
										(BreakInPrint 321 3)
										;You see items which may be interesting and probably valuable.
										(if (not (Btst fStoleCandelabra))
											(BreakInPrint 321 4)
											;The candelabra looks like it is made of gold.
										)
										(if (not (Btst fStoleVase))
											(BreakInPrint 321 5)
											;The vase on the mantle might be worth taking.
										)
										(if (not (Btst fStoleMusicBox))
											(BreakInPrint 321 6)
											;There is also an odd little box on the table.
										)
									)
									(else
										(BreakInPrint 321 2)
										;The room is dark and still.
									)
								)
							)
							((Said '[<up][/ceiling]')
								(BreakInPrint 321 7)
								;How anyone manages to dust a CEILING is beyond you.
							)
							((Said '[<down][/floor,carpet]')
								(BreakInPrint 321 8)
								;The floor is covered with expensive wall-to-wall carpeting not common in this time period.
							)
							((Said '/wall')
								(BreakInPrint 321 9)
								;The walls look like they've been recently painted.
							)
							((Said '/desk')
								(BreakInPrint 321 10)
								;Looks like solid oak.
							)
							((Said '/plant')
								(HighPrint 321 11)
								;Somebody has a green thumb.
							)
							((Said '/dust')
								(BreakInPrint 321 12)
								;There isn't any.
							)
							((Said '/fire,chimney,ember,chandelier')
								(BreakInPrint 321 13)
								;The glowing embers from the fire cast a dim light in the room.
							)
							((Said '/table')
								(if (Btst fStoleMusicBox)
									(BreakInPrint 321 14)
									;There once was a music box on the table, but you took it.
								else
									(HighPrint 321 15)
									;There is an ornately carved box sitting atop the oak table.
								)
							)
							((Said '/mantle')
								(BreakInPrint 321 16)
								;The mantle is a single heavy timber.
								(if (not vaseOutOfWay)
									(BreakInPrint 321 17)
									;A vase sits on the mantle.
								)
							)
							((Said '/painting,portrait')
								(if safeRevealed
									(BreakInPrint 321 18)
									;Over the safe is a charming portrait of the Sheriff and his wife.
								else
									(BreakInPrint 321 19)
									;Over the mantle is a charming portrait of the Sheriff and his wife.
								)
							)
							((Said '/vase,bottle')
								(cond 
									((ego has: iVase)
										(event claimed: FALSE)
									)
									((Btst fStoleVase)
										(HighPrint 321 20)
										;You took it.  Remember?
									)
									((< (ego distanceTo: vase) 50)
										(BreakInPrint 321 21)
										;The vase is carved from alabaster and could be worth some money,
										; although it would take up a lot of space in your pack.
									)
									(else
										(BreakInPrint 321 22)
										;Get closer for a good look.
									)
								)
							)
							((Said '/box')
								(cond 
									((ego has: iMusicBox) (event claimed: FALSE))
									((Btst fStoleMusicBox)
										(HighPrint 321 20)
										;You took it.  Remember?
									)
									((< (ego distanceTo: musicBox) 50)
										(BreakInPrint 321 23)
										;It is a small, beautifully decorated metal box with a hinged lid.
									)
									(else
										(BreakInPrint 321 22)
										;Get closer for a good look.
									)
								)
							)
							((Said '/candle,candelabra,(stick<candle)')
								(cond 
									((ego has: iCandelabra)
										(event claimed: FALSE)
									)
									((Btst fStoleCandelabra)
										(HighPrint 321 20)
										;You took it.  Remember?
									)
									((< (ego distanceTo: candelabra) 70)
										(BreakInPrint 321 24)
										;The candelabra looks like it is made of solid gold.
									)
									(else
										(BreakInPrint 321 22)
										;Get closer for a good look.
									)
								)
							)
							((Said '/alm,silver')
								(BreakInPrint 321 25)
								;Most silvers look alike, and they are all the same weight.
							)
							((Said '/safe')
								(cond 
									((not safeRevealed)
										(BreakInPrint 321 26)
										;Safe? You don't see a safe.
									)
									((not safeOpen)
										(BreakInPrint 321 27)
										;It looks very sturdy, indeed.
									)
									((> (ego distanceTo: safeDoor) 50)
										(BreakInPrint 321 28)
										;Get closer if you want to look into the safe.
									)
									((Btst fSearchedSafe)
										(BreakInPrint 321 29)
										;There is an empty coin bag in the safe.
										)
									(else
										(BreakInPrint 321 30)
										;You see a bag of coins.
									)
								)
							)
						)
					)
					((Said 'move,lift,straighten/painting,portrait')
						(cond 
							(safeRevealed
								(BreakInPrint 321 31)
								;You have already done that.
							)
							((> (ego distanceTo: portrait) 45)
								(NotClose)
							)
							((not vaseOutOfWay)
								(vase setScript: (ScriptID 288 0))
							)
							(else
								(Bset fUncoveredSafe)
								(= safeRevealed TRUE)
								(SolvePuzzle f321MovePainting 1 THIEF)
								(self setScript: (ScriptID 288 4))
							)
						)
					)
					((Said 'lower,replace/painting,portrait')
						(cond 
							(safeOpen
								(BreakInPrint 321 32)
								;You'd better shut the safe, first.
							)
							((not safeRevealed)
								(BreakInPrint 321 33)
								;There is no need to do that.
							)
							((> (ego distanceTo: portrait) 50)
								(NotClose)
							)
							(else
								(= safeRevealed FALSE)
								(self setScript: (ScriptID 288 5))
							)
						)
					)
					(
						(or
							(Said 'move/vase')
							(Said 'get,get/vase/mantle')
							(Said 'place,put/vase/floor')
						)
						(cond 
							((ego has: iVase)
								(BreakInPrint 321 34)
								;It's already in your pack.
							)
							((Btst fStoleVase)
								(HighPrint 321 20)
								;You took it.  Remember?
							)
							((> (ego distanceTo: vase) 40)
								(NotClose)
							)
							(vaseOutOfWay
								(AlreadyDone)
							)
							(else
								(vase posn: 247 136)
								(Bset fMovedVase)
								(= vaseOutOfWay TRUE)
								(BreakInPrint 321 35)
								;You take the vase carefully from the mantle and place it gently on the floor.
							)
						)
					)
					(
						(or
							(Said 'replace/vase')
							(Said 'place,put,move/vase/mantle')
						)
						(cond 
							((ego has: iVase)
								(BreakInPrint 321 34)
								;It's already in your pack.
							)
							((Btst fStoleVase)
								(HighPrint 321 20)
								;You took it.  Remember?
							)
							((> (ego distanceTo: vase) 40)
								(NotClose)
							)
							((not vaseOutOfWay)
								(BreakInPrint 321 36)
								;The vase is on the mantle.
							)
							(else
								(vase posn: 262 119)
								(= vaseOutOfWay FALSE)
								(Bclr fMovedVase)
								(BreakInPrint 321 37)
								;You place the vase back on the mantle.
							)
						)
					)
					((Said 'get>')
						(cond 
							((Said '/vase,bottle')
								(cond 
									((Btst fStoleVase)
										(AlreadyDone)
									)
									((> (ego distanceTo: vase) 35)
										(NotClose)
									)
									(else
										(Bset fStoleVase)
										(ego get: iVase)
										(= vaseOutOfWay TRUE)
										(vase posn: 0 1000 stopUpd:)
										(BreakInPrint 321 38)
										;You place the vase carefully in your pack beneath your cape.
										(SolvePuzzle f321StealVase 1 THIEF)
									)
								)
							)
							((Said '/candle,candelabra,(stick<candle)')
								(cond 
									((Btst fStoleCandelabra)
										(AlreadyDone)
									)
									((> (ego distanceTo: candelabra) 55)
										(NotClose)
									)
									(else
										(Bset fStoleCandelabra)
										(ego get: iCandelabra)
										(candelabra posn: 0 1000 stopUpd:)
										(BreakInPrint 321 39)
										;You place the candelabra carefully in your pack beneath your cape.
										(SolvePuzzle f321StealCandelabra 1 THIEF)
									)
								)
							)
							((Said '/box')
								(cond 
									((Btst fStoleMusicBox)
										(AlreadyDone)
									)
									((> (ego distanceTo: musicBox) 35) (NotClose))
									(else
										(Bset fStoleMusicBox)
										(ego get: iMusicBox)
										(musicBox posn: 0 1000 stopUpd:)
										(if (Btst fOpenMusicBox)
											(miscMusic stop:)
											(BreakInPrint 321 40)
											;You quickly close the box and toss it into your pack.
											(Bclr fOpenMusicBox)
										else
											(BreakInPrint 321 41)
											;You quickly toss the box into your pack.
										)
										(SolvePuzzle f321StealMusicBox 1 THIEF)
									)
								)
							)
							((Said '/plant')
								(if (ego inRect: 0 0 40 108)
									(HighPrint 321 42)
									;Even though it's a 50-year-old Sego palm, it's too big to fit in your pack.
								else
									(NotClose)
								)
							)
							((Said '/alm,silver,loot,bag')
								(cond 
									((ego inRect: 167 127 196 132)
										(if (Btst fSearchedDrawer)
											(AlreadyDone)
										else
											(SearchDrawer)
										)
									)
									((Btst fSearchedSafe)
										(AlreadyDone)
									)
									((not safeRevealed)
										(BreakInPrint 321 43)
										;Nothing like that is visible to you.
									)
									((not safeOpen)
										(BreakInPrint 321 44)
										;There might be something like that inside the wall safe.
									)
									((> (ego distanceTo: safeDoor) 50)
										(NotClose)
									)
									(else
										(Bset fSearchedSafe)
										(ego get: iSilver 50)
										(BreakInPrint 321 45)
										;You take the fifty silvers, and put the empty bag back in the safe.
										(SolvePuzzle f321LootSafe 1 THIEF)
									)
								)
							)
						)
					)
					((Said 'open/safe')
						(cond 
							(safeOpen
								(BreakInPrint 321 46)
								;The safe is already open.
							)
							((not safeRevealed)
								(BreakInPrint 321 26)
								;Safe? You don't see a safe.
							)
							((> (ego distanceTo: safeDoor) 50)
								(NotClose)
							)
							(else
								(BreakInPrint 321 47)
								;The safe is securely locked.
							)
						)
					)
					((Said 'unlock,lockpick,crack/safe,hasp')
						(cond 
							((not safeRevealed)
								(BreakInPrint 321 48)
								;Where?
							)
							((> (ego distanceTo: safeDoor) 50)
								(NotClose)
							)
							(safeOpen
								(BreakInPrint 321 49)
								;What for? It's open!
							)
							((== safeCrackSuccess 2)
								(ego setScript: (ScriptID 289 0)))
							((TrySkill PICK 0 (- lockPickBonus 20))
								(miscMusic number: (SoundFX 35) loop: 1 play:)
								(HighPrint 321 50)
								;Ah, got it!
								(++ safeCrackSuccess)
								(safeDoor setCel: 1)
								(Bset fCrackedSafe)
								(= safeOpen TRUE)
								(SolvePuzzle f321CrackSafe 1 THIEF)
							)
							(else
								(HighPrint 321 51)
								;Cracking safes looked a lot easier in the instruction book.
							)
						)
					)
					((Said 'close/safe')
						(cond 
							((not safeRevealed)
								(BreakInPrint 321 26)
								;Safe? You don't see a safe.
							)
							((not safeOpen)
								(BreakInPrint 321 52)
								;It's not open.
							)
							((> (ego distanceTo: safeDoor) 50)
								(NotClose)
							)
							(else (Bclr fCrackedSafe)
								(= safeOpen FALSE)
								(safeDoor setCel: 0)
							)
						)
					)
					((Said 'open/door')
						(cond 
							((== (ego onControl: origin) cYELLOW)
								(leftDoor setScript: (ScriptID 290 1))
							)
							((== (ego onControl: origin) cLMAGENTA)
								(rightDoor setScript: (ScriptID 290 2))
							)
							((== (ego onControl: origin) cLRED)
								(bottomDoor setScript: (ScriptID 290 0))
							)
							(else
								(NotClose)
							)
						)
					)
					((Said 'lift,open/box,lid')
						(cond 
							((ego has: iMusicBox)
								(BreakInPrint 321 53)
								;The little box is safely tucked away in your pack. You can open it up later.
							)
							((Btst fStoleMusicBox)
								(HighPrint 321 20)
								;You took it.  Remember?
								)
							((> (ego distanceTo: musicBox) 35)
								(NotClose)
							)
							((Btst fOttoClosedMusicBox)
								(BreakInPrint 321 54)
								;Oh, no! You won't do THAT again!
							)
							(else
								(ego setScript: (ScriptID 289 1))
							)
						)
					)
					((Said 'close,lower/box,lid')
						(cond 
							((ego has: iMusicBox)
								(BreakInPrint 321 55)
								;The little box is safely tucked away in your pack. It is already closed.
							)
							((Btst fStoleMusicBox)
								(HighPrint 321 20)
								;You took it.  Remember?
								)
							((> (ego distanceTo: musicBox) 35)
								(NotClose)
							)
							((Btst fOpenMusicBox)
								(miscMusic stop:)
								(BreakInPrint 321 56)
								;You quickly shut the lid on the music box!
								(Bclr fOpenMusicBox)
								(musicBox setCel: 3)
							)
							(else
								(BreakInPrint 321 57)
								;It's already closed.
							)
						)
					)
					((Said 'listen[/!*,door,snore]')
						(cond 
							((== (ego onControl: origin) cYELLOW)
								(BreakInPrint 321 58)
								;There is some fairly loud snoring going on in this room. You hear: "Brckawwwww...zup-zup-zup-zup-zzzzz."
							)
							((== (ego onControl: origin) cLMAGENTA)
								(BreakInPrint 321 59)
								;Very faintly, you hear snoring: "Prrrrrt...prrrrrt...prrrrrt."
							)
							((== (ego onControl: origin) cLRED)
								(BreakInPrint 321 60)
								;Someone (or someTHING) is snoring up a storm. You hear: "Nkaawww..rspft...honk... buh-buh-buh-buh-buh-buh-phweep.
							)
							(else
								(BreakInPrint 321 61)
								;Faintly, you hear: "Nkaawww...prrrrrt... honk...zup-zup-zup-buh-buh-buh-phweep.
							)
						)
					)
					((Said 'odor>')
						(cond 
							((Said '/sauerkraut,bratwurst,food')
								(BreakInPrint 321 62)
								;If you were a person who liked that sort of food, you'd love this smell!
							)
							((Said '/smoke,birch,fire')
								(BreakInPrint 321 63)
								;It would be more pleasant if it wasn't mixed with the odor of sauerkraut.
							)
							((Said '/odor')
								(BreakInPrint 321 64)
								;What a mixture!
							)
						)
					)
				)
			)
		)
	)
)

(instance sneakMusic of Sound
	(properties
		number 8
		loop -1
	)
)

(instance miscMusic of Sound
	(properties
		number 13
		priority 1
	)
)

(instance onDesk of RFeature
	(properties
		nsTop 89
		nsLeft 160
		nsBottom 124
		nsRight 193
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onDesk event shiftDown)
				(BreakInPrint 321 10)
				;Looks like solid oak.
			)
		)
	)
)

(instance onCandles of RFeature
	(properties
		nsTop 75
		nsLeft 166
		nsBottom 88
		nsRight 179
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onCandles event shiftDown)
				(cond 
					((ego has: iCandelabra)
						(event claimed: FALSE)
					)
					((Btst fStoleCandelabra)
						(event claimed: FALSE)
					)
					((< (ego distanceTo: candelabra) 70)
						(BreakInPrint 321 24)
						;The candelabra looks like it is made of solid gold.
					)
					(else
						(BreakInPrint 321 22)
						;Get closer for a good look.
					)
				)
			)
		)
	)
)

(instance onVase of RFeature
	(properties
		nsTop 107
		nsLeft 258
		nsBottom 119
		nsRight 267
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onVase event shiftDown)
				(cond 
					((ego has: iVase)
						(event claimed: FALSE)
					)
					((Btst fStoleVase)
						(event claimed: FALSE)
					)
					((< (ego distanceTo: vase) 50)
						(BreakInPrint 321 21)
						;The vase is carved from alabaster and could be worth some money,
						; although it would take up a lot of space in your pack.
					)
					(else
						(BreakInPrint 321 22)
						;Get closer for a good look.
					)
				)
			)
		)
	)
)

(instance onMusicBox of RFeature
	(properties
		nsTop 139
		nsLeft 134
		nsBottom 145
		nsRight 141
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onMusicBox event shiftDown)
				(cond 
					((ego has: iMusicBox)
						(event claimed: FALSE)
					)
					((Btst fStoleMusicBox)
						(event claimed: FALSE)
					)
					((< (ego distanceTo: musicBox) 50)
						(BreakInPrint 321 23)
						;It is a small, beautifully decorated metal box with a hinged lid.
					)
					(else
						(BreakInPrint 321 22)
						;Get closer for a good look.
					)
				)
			)
		)
	)
)

(instance onFireplace of RFeature
	(properties
		nsTop 119
		nsLeft 254
		nsBottom 155
		nsRight 279
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onFireplace event shiftDown)
				(BreakInPrint 321 13)
				;The glowing embers from the fire cast a dim light in the room.
			)
		)
	)
)

(instance onPaintingDown of RFeature
	(properties
		nsTop 91
		nsLeft 267
		nsBottom 120
		nsRight 287
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onPaintingDown event shiftDown)
				(if safeRevealed
					(event claimed: FALSE)
				else
					(BreakInPrint 321 19)
					;Over the mantle is a charming portrait of the Sheriff and his wife.
				)
			)
		)
	)
)

(instance onPaintingUp of RFeature
	(properties
		nsTop 77
		nsLeft 262
		nsBottom 108
		nsRight 287
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onPaintingUp event shiftDown)
				(if safeRevealed
					(BreakInPrint 321 18)
					;Over the safe is a charming portrait of the Sheriff and his wife.
				else
					(event claimed: FALSE)
				)
			)
		)
	)
)

(instance onSafe of RFeature
	(properties
		nsTop 106
		nsLeft 270
		nsBottom 117
		nsRight 281
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onSafe event shiftDown)
				(cond 
					((not safeRevealed)
						(event claimed: FALSE)
					)
					((not safeOpen)
						(BreakInPrint 321 27)
						;It looks very sturdy, indeed.
					)
					((> (ego distanceTo: safeDoor) 50)
						(BreakInPrint 321 28)
						;Get closer if you want to look into the safe.
					)
					((Btst fSearchedSafe)
						(BreakInPrint 321 29)
						;There is an empty coin bag in the safe.
					)
					(else
						(BreakInPrint 321 30)
						;You see a bag of coins.
					)
				)
			)
		)
	)
)

(instance onPlant of RFeature
	(properties
		nsTop 32
		nsBottom 83
		nsRight 10
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onPlant event shiftDown)
				(HighPrint 321 65)
				;It's a potted plant.
			)
		)
	)
)

(instance onLeftDoor of RFeature
	(properties
		nsTop 6
		nsLeft 105
		nsBottom 54
		nsRight 130
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onLeftDoor event shiftDown)
				(BreakInPrint 321 58)
				;There is some fairly loud snoring going on in this room. You hear: "Brckawwwww...zup-zup-zup-zup-zzzzz."
			)
		)
	)
)

(instance onRightDoor of RFeature
	(properties
		nsTop 6
		nsLeft 150
		nsBottom 54
		nsRight 175
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onRightDoor event shiftDown)
				(BreakInPrint 321 59)
				;Very faintly, you hear snoring: "Prrrrrt...prrrrrt...prrrrrt."
			)
		)
	)
)

(instance onBottomDoor of RFeature
	(properties
		nsTop 69
		nsLeft 108
		nsBottom 118
		nsRight 142
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onBottomDoor event shiftDown)
				(BreakInPrint 321 61)
				;Faintly, you hear: "Nkaawww...prrrrrt... honk...zup-zup-zup-buh-buh-buh-phweep.
			)
		)
	)
)

(instance onChair of RFeature
	(properties
		nsTop 133
		nsLeft 285
		nsBottom 160
		nsRight 297
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onChair event shiftDown)
				(HighPrint 321 66)
				;An uncomfortable looking chair.
			)
		)
	)
)

(instance onCouch of RFeature
	(properties
		nsTop 131
		nsLeft 96
		nsBottom 160
		nsRight 122
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onCouch event shiftDown)
				(HighPrint 321 67)
				;An overstuffed couch.
			)
		)
	)
)

(instance onTable of RFeature
	(properties
		nsTop 138
		nsLeft 125
		nsBottom 152
		nsRight 151
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onMusicBox event shiftDown)
				(cond 
					((ego has: iMusicBox)
						(HighPrint 321 68)
						;A solid looking table.
					)
					((Btst fStoleMusicBox)
						(HighPrint 321 68)
						;A solid looking table.
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((MouseClaimed onTable event shiftDown)
				(HighPrint 321 68)
				;A solid looking table.
			)
		)
	)
)

(instance onStuffedChair of RFeature
	(properties
		nsTop 98
		nsLeft 210
		nsBottom 124
		nsRight 236
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onStuffedChair event shiftDown)
				(HighPrint 321 69)
				;An overstuffed chair.
			)
		)
	)
)
