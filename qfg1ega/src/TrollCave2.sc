;;; Sierra Script 1.0 - (do not remove this comment)
(script# 89)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm89 0
)
(enum
	trollABSENT
	trollVISIBLE
	trollDYING
	trollDEAD
)

(enum
	searchTROLL
	searchBEARD
)

(local
	trollState
	local1
	searchWhat
	[local3 8]
)
(instance magicHit of Sound
	(properties
		number 45
		priority 1
	)
)

(instance egoShoots of Sound
	(properties
		number 33
		priority 2
	)
)

(instance rm89 of Room
	(properties
		picture 89
		style WIPELEFT
		east 93
		west 88
	)
	
	(method (init)
		(cSound number: 23 loop: -1 play:)
		(super init:)
		(SolvePuzzle POINTS_ENTERSECRETENTRANCE 2)
		(StatusLine enable:)
		(NormalEgo)
		(ego init:)
		(if
			(==
				(= trollState
					(cond 
						((== prevRoomNum vTroll)
							(= monsterNum 0)
							trollDYING
						)
						((Btst DEFEATED_FRED_89)
							(troll
								view: vTrollDefeated
								posn: 65 105
								setLoop: 0
								setCel: 10
								init:
								addToPic:
							)
							trollDEAD
						)
						((or (Btst SAID_HIDEN_GOSEKE) (Btst DEFEATED_FRED))
							trollABSENT
						)
						(else
							trollVISIBLE
						)
					)
				)
				1
			)
			(Load VIEW vTroll)
			(Load SCRIPT CHASE)
			(if (ego knows: FLAMEDART)
				(Load VIEW vEgoMagicFlameDart)
				(Load SOUND (SoundFX 33))
				(Load SOUND (SoundFX 45))
				(egoShoots number: (SoundFX 33) init:)
				(magicHit number: (SoundFX 45) init:)
			)
		)
		(switch prevRoomNum
			(vTroll
				(ego posn: 94 115 loop: 1 cel: 4)
				(self setScript: trollDies)
			)
			(84
				(if (== trollState trollVISIBLE)
					(self setScript: trollAttacks)
				else
					(self setScript: fromAntwerp)
				)
			)
			(93
				(ego posn: 318 163 setMotion: MoveTo 279 164)
			)
			(else 
				(ego posn: 6 115 setMotion: MoveTo 38 115)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (== (ego onControl: origin) cLCYAN)
			(curRoom newRoom: 84)
		)
	)
	
	(method (dispose)
		(Bset fBeenIn89)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '/down,ground,floor,stalactite')
								(HighPrint 89 0)
								;You can watch the water ooze down the sides of the stalactites and drip to the ground.
							)
							((Said '/up,ceiling,stalagmite')
								(HighPrint 89 1)
								;You can watch the water drop down from the stalactites and ooze down the sides of the stalagmites.
							)
							((Said '[<at,around][/!*,cave,room]')
								(Print 89 2 #at -1 120 #width 300 #mode teJustCenter)
								;As your eyes adjust to the darkness, you see by the light of the fungus that this passageway twists
								;its way through the mountain.  It is cold in here, and you can smell stagnant water and faint hints of decay.
							)
							((Said '/water')
								(HighPrint 89 3)
								;The water lies in stagnant pools.
							)
							((Said '/troll,monster,creature')
								(switch trollState
									(trollVISIBLE
										(HighPrint 89 4)
										;A large Troll looms before you, a snarl on his lips.
									)
									(trollDYING
										(HighPrint 89 5)
										;The large Troll lies dying upon the slimy floor of the cave.
									)
									(trollDEAD
										(HighPrint 89 6)
										;A large, dead Troll lies in a pool of blue blood.
									)
									(else
										(HighPrint 89 7)
										;You see no such creature here.
									)
								)
							)
							((Said '/north,west,south,east')
								(HighPrint 89 8)
								;You have lost your sense of direction, as the cave passage twists around.
							)
							((Said '/boulder')
								(HighPrint 89 9)
								;The rocks look slick and are slimy to the touch.
							)
							((Said '/fungus')
								(HighPrint 89 10)
								;The light from the fungus is eerie and vaguely unpleasant.
							)
							((Said '/entrance,open')
								(HighPrint 89 11)
								;The only entrance to this cave is the one you came through.
							)
						)
					)
					((Said 'hiden')
						(HighPrint 89 12)
						;There's no need for the password now.
					)
					((Said 'throw/')
						(HighPrint 89 13)
						;There is nothing here to throw it at.
					)
					((Said 'search/troll,monster,creature,body,enemy')
						(cond 
							((!= trollState trollDEAD)
								(HighPrint 89 14)
								;You can't do that.
							)
							((ego inRect: 54 78 100 125) (= searchWhat 0) (self setScript: egoSearch))
							(else
								(HighPrint 89 15)
								;You need to get closer to the dead Troll.
							)
						)
					)
					((Said 'get,get>')
						(cond 
							((Said '/hair,beard')
								(cond 
									((!= trollState trollDEAD)
										(HighPrint 89 14)
										;You can't do that.
									)
									((ego inRect: 54 78 100 125)
										(= searchWhat searchBEARD)
										(self setScript: egoSearch)
									)
									(else
										(HighPrint 89 15)
										;You need to get closer to the dead Troll.
									)
								)
							)
							((Said '/club,weapon')
								(cond 
									((== trollState trollVISIBLE)
										(HighPrint 89 16)
										;You're kidding, right?
									)
									((!= trollState trollDEAD)
										(HighPrint 89 14)
										;You can't do that.
									)
									(else
										(HighPrint 89 17)
										;The dead Troll's huge club is much too heavy for you to lift.
									)
								)
							)
							((Said '/fungus')
								(HighPrint 89 18)
								;The fungus is slimy and stuck tight to the cave walls.
							)
							((Said '/troll,stalactite,stalagmite')
								(HighPrint 89 16)
								;You're kidding, right?
							)
							((Said '/boulder,water')
								(HighPrint 89 19)
								;You don't need it.
							)
						)
					)
					((Said 'feed/troll,monster,creature')
						(if (== trollState trollVISIBLE)
							(HighPrint 89 20)
							;The large Troll looks as though he'd rather eat YOU!
						else
							(HighPrint 89 14)
							;You can't do that.
						)
					)
					((Said 'listen/')
						(HighPrint 89 21)
						;You hear the constant "drip... drip..." of water.
					)
					((Said 'cast>')
						(= spell (SaidSpell event))
						(if (CastSpell spell)
							(switch spell
								(DETMAGIC
									(HighPrint 89 22)
									;There is no magic in this cave.
								)
								(DAZZLE
									(HighPrint 89 23)
									;There is nothing here to dazzle.
								)
								(FLAMEDART
									(HighPrint 89 24)
									;There is nothing here to use it on.
								)
								(CALM
									(HighPrint 89 25)
									;There is nothing here to calm.
								)
								(OPEN
									(HighPrint 89 26)
									;There is nothing here to open.
								)
								(else
									(HighPrint 89 27)
									;That spell is useless here.
								)
							)
						)
					)
					((Said 'fight')
						(if (Btst DEFEATED_FRED)
							(HighPrint 89 28)
							;The cave troll is dead.
						else
							(curRoom newRoom: vTroll)
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance troll of Actor
	(properties
		yStep 3
		view vTroll
		cycleSpeed 1
		illegalBits $0000
		xStep 5
		moveSpeed 1
	)
)

(instance trollAttacks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 293 59 setMotion: MoveTo 261 63)
				(troll
					posn: 179 77
					init:
					setCycle: Walk
					setMotion: MoveTo 225 68 self
				)
			)
			(1
				(HighPrint 89 29)
				;Before you can react, the Troll is upon you, and the encounter begins.
				(curRoom newRoom: vTroll)
			)
		)
	)
)

(instance trollDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset DEFEATED_FRED_89)
				(Bset DEFEATED_FRED)
				(HandsOff)
				(troll
					view: vTrollDefeated
					loop: 0
					cel: 0
					illegalBits: 0
					posn: 65 105
					init:
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(1
				(HandsOn)
				(= trollState trollDEAD)
				(troll addToPic:)
				(client setScript: 0)
			)
		)
	)
)

(instance egoSearch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego troll)
				(ego
					loop: (mod (+ (ego loop?) 4) 2)
					view: vEgoThrowing
					setCycle: EndLoop self
				)
			)
			(1
				(switch searchWhat
					(searchTROLL
						(if (Btst fShavedFred)
							(HighPrint 89 30)
							;You find nothing on the Troll.
						else
							(HighPrint 89 31)
							;You find nothing on the Troll but a little hair.
						)
					)
					(searchBEARD
						(if (Btst fShavedFred)
							(HighPrint 89 32)
							;You find no more beard hair on the dead Troll.
						else
							(HighPrint 89 33)
							;You cut off the Troll's beard and put it away.
							(Bset fShavedFred)
							(ego get: iTrollBeard)
						)
					)
				)
				(ego setCycle: BegLoop self)
			)
			(2
				(NormalEgo)
				(HandsOn)
			)
		)
	)
)

(instance fromAntwerp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 293 59 setMotion: MoveTo 261 63 self)
			)
			(1
				(if (not (Btst fBeenIn89))
					(Print 89 2 #at -1 120 #width 300 #mode teJustCenter)
					; As your eyes adjust to the darkness, you see by the light of the fungus that this passageway twists its way through the mountain.
					;It is cold in here, and you can smell stagnant water and faint hints of decay.
				)
			)
		)
	)
)
