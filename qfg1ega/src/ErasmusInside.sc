;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use RFeature)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm30 0
)

(local
	goingUpstairs
	warned
	warpCued
	local3
	roomForbidden
	leaving
)
(procedure (FenrusTurnsHead param1)
	(if (and (!= local3 param1) (== (fenrus loop?) 1))
		(= local3 param1)
		(fenrus cel: param1 forceUpd:)
	)
)

(procedure (LookRoom)
	(HighPrint 30 0)
	;This room is full of odds, ends, and stuff.  The wizard must be a real character.
)

(procedure (LookEastRoom)
	(HighPrint 30 1)
	;Through the portal to the east is a large formal dining room.
)

(procedure (LookWestRoom)
	(HighPrint 30 2)
	;Through the portal to the west is a small sitting room.
)

(procedure (LookEastWall)
	(HighPrint 30 3)
	;Against the east wall, you see a bookcase and a desk, above which is hung a dragon's head trophy.
	;These are on either side of a doorway, above which hangs a shield.
	(LookEastRoom)
)

(procedure (LookWestWall)
	(HighPrint 30 4)
	;Leaning against the west wall is a mummy case.  Above that hangs an ancient tapestry.
	(LookWestRoom)
)

(procedure (LookSouthWall)
	(HighPrint 30 5)
	;The wall is plain.  You see nothing but the front door of the house.
)

(procedure (LookNorthWall)
	(HighPrint 30 6)
	;Above the staircase hangs a portrait, flanked by decorative wall hangings.  In an alcove on the landing stands a suit of armor.
	(HighPrint 30 7)
	;There is a table up against the staircase wall, and on the wall hangs an onklunk.
)

(instance Magic of Prop
	(properties
		view vTeleportPink
		cycleSpeed 2
	)
)

(instance rm30 of Room
	(properties
		picture 30
		style HSHUTTER
	)
	
	(method (init)
		(LoadMany VIEW vTeleportPink vWizardLobby vFenrus)
		(super init:)
		(mouseDownHandler add: self)
		(SolvePuzzle f30EnterTower 3)
		(StatusLine enable:)
		(NormalEgo)
		(ego posn: 160 189 init: setScript: intro)
		(addToPics
			add: theFrame bigChair smChair hang1 hang2 lunk plane armor mummy shelf
			eachElementDo: #init
			doit:
		)
		(theDunker
			setPri: 14
			posn: 112 160
			init:
			startUpd:
			setScript: dunker
		)
		(peacock posn: 186 122 init: stopUpd:)
		(dragon posn: 282 116 init: stopUpd:)
		(theLight
			setPri: 9
			posn: 161 102
			init:
			setCycle: Forward
			startUpd:
			setScript: lightBulb
		)
		(theWiz setPri: 5 posn: 146 72 init: stopUpd:)
		(fenrus setPri: 1 posn: 82 50 init: stopUpd:)
	)
	
	(method (doit)
		(if warpCued
			(= warpCued FALSE)
			(self setScript: teleportOut)
		)
		(cond 
			(
				(and
					(== (ego onControl: origin) cLMAGENTA)
					(== (ego loop?) 0)
					(not goingUpstairs)
				)
				(= goingUpstairs TRUE)
				(ego setScript: intoTheTower)
			)
			((and (== (ego edgeHit?) SOUTH) (not leaving))
				(= leaving TRUE)
				(= warpCued TRUE)
			)
			((and (== (ego onControl: origin) cLRED) (not roomForbidden))
				(= roomForbidden TRUE)
				(= warpCued TRUE)
			)
		)
		(cond 
			((<= (ego x?) 120) (FenrusTurnsHead 0))
			((<= (ego x?) 180) (FenrusTurnsHead 1))
			(else (FenrusTurnsHead 2))
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn30)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(cond 
			((super handleEvent: event))
			(
				(or
					(Said 'look/dragon,head,trophy')
					(MouseClaimed dragon event shiftDown)
				)
				(HighPrint 30 8)
				;It was a moderately large and fearful dragon at one time.
			)
			(
				(or
					(Said 'look/chandelier,ball')
					(MouseClaimed theLight event shiftDown)
				)
				(HighPrint 30 9)
				;The Ball of Light floats magically over the center of the room.
			)
			(
				(or
					(Said 'look/peacock,bird')
					(MouseClaimed peacock event shiftDown)
				)
				(if (> (peacock cel?) 0)
					(HighPrint 30 10)
					;You admire the plumage on the peacock.
					(showFeathers changeState: 2)
				else
					(HighPrint 30 11)
					;The poor stuffed peacock seems bedraggled, worn, and over-used.
					(showFeathers changeState: 0)
				)
			)
			(
				(or
					(Said 'look/toy,(dragon<little),drink')
					(MouseClaimed theDunker event shiftDown)
				)
				(HighPrint 30 12)
				;You can make out the runes imprinted on the\nstrange device:  "Acme Toy Co."
				(HighPrint 30 13)
				;Oh my!  It's a Dunking Dragon!  You thought they were only to be found in myths and legends.
			)
			(
				(or
					(Said 'look/desk')
					(MouseClaimed onDesk event shiftDown)
				)
				(HighPrint 30 14)
				;It is a standard roll-top desk.  There is nothing on it.
			)
			(
				(or
					(Said 'look/armor,chainmail')
					(MouseClaimed armor event shiftDown)
				)
				(HighPrint 30 15)
				;The suit of armor was made for someone much taller than you.
				;The plaque underneath it reads:
				;"Bequeathed by Colonel Gulden Dijon."
			)
			(
				(or
					(Said 'look/airplane')
					(MouseClaimed plane event shiftDown)
				)
				(HighPrint 30 16)
				;You have no idea what it is, but it has the words "Lytton P. D." inscribed upon it.
			)
			(
				(or
					(Said 'look/mummy,coffin')
					(MouseClaimed mummy event shiftDown)
				)
				(HighPrint 30 17)
				;Dredging up your ancient Egyptian, you translate the hieroglyphs: "She of the golden hair."
				(HighPrint 30 18)
				;This is an excerpt from the Rosella Stone!  There is a picture next to the message.
				(Print 30 19 #title {The Picture.} #icon vWizardLobby 6 4)
				;(Picture of Rosella from KQ4 doing an Egyptian pose.)
			)
			(
				(or
					(Said 'look/onklunk')
					(MouseClaimed lunk event shiftDown)
				)
				(HighPrint 30 20)
				;By golly, it's a rare Peruvian Onklunk!
			)
			(
				(or
					(Said 'look/painting,portrait,wizard,erasmus')
					(MouseClaimed theFrame event shiftDown)
				)
				(if (Btst fBeenIn31)
					(HighPrint 30 21)
					;You wonder if the portrait of Erasmus is really a painting, or something completely different.
				else
					(HighPrint 30 22)
					;Your eyes are drawn to the eyes of the figure in the portrait.  His intense stare is unnerving.
					(HighPrint 30 23)
					;This would not be someone to trifle with, for he looks subtle and quick to anger.
				)
			)
			(
				(or
					(Said 'look/chest')
					(MouseClaimed onChest event shiftDown)
				)
				(HighPrint 30 24)
				;This item appears to be an ebony chest. It could contain anything.
			)
			(
				(or
					(Said 'look/table')
					(MouseClaimed onTable event shiftDown)
				)
				(HighPrint 30 25)
				;The table is bare, except for a stuffed bird which faces the door.
			)
			(
				(or
					(Said 'look/bookcase,case,book,shelf')
					(MouseClaimed onBookcase event shiftDown)
				)
				(HighPrint 30 26)
				;The books on the shelves of the bookcase appear to be very old, very rare, and very magical.
				(HighPrint 30 27)
				;You find titles like:  "Zen and the Art of Magical Maintenance",  "How to Be a Halfling",
				; "The Sensuous Succubus",  and "Tobin's Spirit Guide, Volume II:  Asmodeus to Casper".
			)
			((MouseClaimed onWest event shiftDown)
				(LookWestRoom)
			)
			(
				(and
					(MouseClaimed onEast event shiftDown)
					(not (MouseClaimed dragon event shiftDown))
				)
				(LookEastRoom)
			)
			(
				(or
					(Said 'look/shield')
					(MouseClaimed onShield event shiftDown)
				)
				(HighPrint 30 28)
				;The shield looks like it belonged to one of those "Once and Future" kings.
			)
			((MouseClaimed fenrus event shiftDown)
				(HighPrint 30 29)
				;There is a strange figure on a shelf above the stairs.  It looks like an overgrown rat wearing a wizard's hat.
			)
			((MouseClaimed onTapestry event shiftDown)
				(HighPrint 30 30) ;EO: "It's" is used where "Its" should be
				;An adumbrated wall hanging.  It's design evokes thoughts of ancient times and battles past.
			)
			(128
				(cond 
					((Said 'look>')
						(cond 
							((Said '[<around,at][/room,house,foyer,!*]')
								(LookRoom)
							)
							((or (Said '/east') (Said '/wall<east'))
								(LookEastWall)
							)
							((or (Said '/west') (Said '/wall<west'))
								(LookWestWall)
							)
							((or (Said '/north') (Said '/wall<north'))
								(LookNorthWall)
							)
							((or (Said '/south') (Said '/wall<south'))
								(LookSouthWall)
							)
							((Said '/wall')
								(switch (ego loop?)
									(loopE
										(LookEastWall)
									)
									(loopW
										(LookWestWall)
									)
									(loopS
										(LookSouthWall)
									)
									(loopN
										(LookNorthWall)
									)
								)
							)
							((Said '/chair')
								(HighPrint 30 31)
								;There are two finely crafted chairs, one larger than the other.
							)
							((Said '/trunk,box')
								(HighPrint 30 32)
								;Beneath the "Dunking Dragon" is an old steamer trunk.
							)
							((Said '/door')
								(HighPrint 30 33)
								;The only actual door visible is the door leading back outside.
								(LookEastRoom)
								(LookWestRoom)
							)
							((Said '/ladder,staircase')
								(HighPrint 30 34)
								;The staircase connects the upstairs to the downstairs.
							)
							((Said '/tapestry,hang')
								(HighPrint 30 35)
								;The pattern is obscured by age.
							)
							((or (Said '/ceiling,roof,sconce') (Said '<up'))
								(HighPrint 30 36)
								;The high ceiling has ornate sconcing and scrollwork.
							)
							((or (Said '/floor') (Said '<down'))
								(HighPrint 30 37)
								;Purple planking?
							)
						)
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(DETMAGIC
								(if (CastSpell spell)
									(HighPrint 30 38)
									;You detect a strange, magical aura in this place.
								)
							)
							(DAZZLE
								(if (CastSpell spell)
									(HighPrint 30 39)
									;There's nothing here to dazzle.
								)
							)
							(FLAMEDART
								(if (CastSpell spell)
									(if warned
										(= warpCued TRUE)
									else
										(dragon setScript: warning)
									)
								)
							)
							(OPEN
								(if (CastSpell spell)
									(if warned
										(= warpCued TRUE)
									else
										(dragon setScript: warning)
									)
								)
							)
						)
					)
					(
						(or
							(Said 'open/desk,drawer,chest,box,bookcase')
							(Said 'sat,get,throw,grab')
						)
						(if warned
							(= warpCued TRUE)
						else
							(dragon setScript: warning)
						)
					)
					((or (Said 'nap[/!*]') (Said 'go[<to]/nap'))
						(HighPrint 30 40)
						;The wizard does not run an inn.
						(= warpCued TRUE)
					)
				)
			)
		)
	)
)

(instance showFeathers of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (peacock setCycle: EndLoop self))
			(1 (peacock stopUpd:))
			(2 (peacock setCycle: BegLoop self))
			(3 (peacock stopUpd:))
		)
	)
)

(instance dunker of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(theDunker setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(2 (= seconds 5))
			(3
				(theDunker setLoop: 5 cel: 0 setCycle: EndLoop self)
			)
			(4 (self changeState: 0))
		)
	)
)

(instance lightBulb of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theLight y: 102)
				(= cycles 16)
			)
			(1
				(theLight y: 103)
				(= cycles 16)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance intro of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 160 189 setMotion: MoveTo 160 184 self)
			)
			(1
				(if (not (Btst fBeenIn30))
					(LookRoom)
				)
				(ego setScript: 0)
			)
		)
	)
)

(instance intoTheTower of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 112 112 self)
			)
			(1
				(ego setMotion: MoveTo 205 70 self)
			)
			(2
				(ego setMotion: MoveTo 215 70 self)
			)
			(3
				(ego setMotion: MoveTo 215 67 self)
			)
			(4
				(fenrus setLoop: 0 cycleSpeed: 2 setCycle: EndLoop)
				(ego setPri: 1 setMotion: MoveTo 189 62 self)
			)
			(5
				(theWiz setCycle: EndLoop)
				(= seconds 3)
			)
			(6
				(curRoom newRoom: 31)
			)
		)
	)
)

(instance warning of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(dragon setCel: -1 setCycle: Forward)
				(Print 30 41 #at 215 43 #width 75 #dispose #time 4)
				(= seconds 3)
				; I wouldn't do that if I were you!
			)
			(1
				(= warned TRUE)
				(HandsOn)
				(dragon setCycle: 0 setCel: 0 stopUpd: setScript: 0)
			)
		)
	)
)

(instance teleportOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (< (fenrus cel?) 2)
					(fenrus setCycle: CycleTo 2 1 self)
				else
					(self cue:)
				)
			)
			(1
				(fenrus setLoop: 2 setCycle: Forward)
				(= cycles 2)
			)
			(2
				(Magic
					ignoreActors:
					setPri: (ego priority?)
					posn: (ego x?) (ego y?)
					init:
					setCycle: CycleTo 6 1 self
				)
			)
			(3
				(dragon setCel: -1 setCycle: Forward)
				(cond 
					(roomForbidden
						(Print 30 42 #at 215 43 #width 90 #dispose #time 3)
						; "Those rooms are forbidden!"
					)
					(leaving
						(Print 30 43 #at 215 43 #width 90 #dispose #time 3)
						; "Since you're leaving anyway..."
					)
					(else
						(Print 30 44 #at 215 43 #width 90 #dispose #time 3)
						; "That'll learn ya!"
					)
				)
				(ego hide:)
				(Magic setCycle: EndLoop)
				(= seconds 4)
			)
			(4
				(Bset fErasmusWarpOut)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 (| BLACKOUT IRISOUT))
				(self cue:)
			)
			(5
				(curRoom newRoom: 28)
			)
		)
	)
)

(instance theDunker of Prop
	(properties
		view vWizardLobby
		loop 4
		cycleSpeed 2
	)
)

(instance peacock of Prop
	(properties
		view vWizardLobby
		loop 7
		cycleSpeed 1
	)
)

(instance dragon of Prop
	(properties
		view vWizardLobby
		loop 2
		cycleSpeed 2
	)
)

(instance theLight of Prop
	(properties
		view vWizardLobby
		loop 1
		cycleSpeed 1
	)
)

(instance theWiz of Prop
	(properties
		view vWizardLobby
		loop 8
		cycleSpeed 2
	)
)

(instance fenrus of Prop
	(properties
		view vFenrus
		loop 1
	)
)

(instance bigChair of PicView
	(properties
		y 151
		x 210
		view vWizardLobby
		loop 3
	)
)

(instance smChair of PicView
	(properties
		y 182
		x 248
		view vWizardLobby
		loop 3
		cel 1
	)
)

(instance hang1 of PicView
	(properties
		y 48
		x 115
		view vWizardLobby
		loop 3
		cel 2
	)
)

(instance hang2 of PicView
	(properties
		y 48
		x 176
		view vWizardLobby
		loop 3
		cel 3
		priority 4
	)
)

(instance theFrame of RPicView
	(properties
		y 75
		x 146
		view vWizardLobby
	)
)

(instance lunk of RPicView
	(properties
		y 109
		x 222
		view vWizardLobby
		loop 6
	)
)

(instance plane of RPicView
	(properties
		y 183
		x 102
		view vWizardLobby
		loop 6
		cel 1
		priority 14
	)
)

(instance armor of RPicView
	(properties
		y 110
		x 83
		view vWizardLobby
		loop 6
		cel 2
	)
)

(instance mummy of RPicView
	(properties
		y 166
		x 20
		view vWizardLobby
		loop 6
		cel 3
	)
)

(instance shelf of PicView
	(properties
		y 54
		x 81
		view vWizardLobby
		loop 3
		cel 4
	)
)

(instance onDesk of RFeature
	(properties
		nsTop 130
		nsLeft 266
		nsBottom 189
		nsRight 319
	)
)

(instance onChest of RFeature
	(properties
		nsTop 151
		nsBottom 189
		nsRight 82
	)
)

(instance onTable of RFeature
	(properties
		nsTop 116
		nsLeft 125
		nsBottom 136
		nsRight 175
	)
)

(instance onBookcase of RFeature
	(properties
		nsTop 78
		nsLeft 237
		nsBottom 147
		nsRight 258
	)
)

(instance onShield of RFeature
	(properties
		nsTop 19
		nsLeft 263
		nsBottom 55
		nsRight 275
	)
)

(instance onEast of RFeature
	(properties
		nsTop 60
		nsLeft 264
		nsBottom 129
		nsRight 275
	)
)

(instance onWest of RFeature
	(properties
		nsTop 61
		nsLeft 43
		nsBottom 150
		nsRight 53
	)
)

(instance onTapestry of RFeature
	(properties
		nsTop 14
		nsLeft 10
		nsBottom 95
		nsRight 28
	)
)
