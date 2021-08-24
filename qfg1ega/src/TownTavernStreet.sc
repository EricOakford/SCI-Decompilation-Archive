;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
(include game.sh)
(use Main)
(use Door)
(use LoadMany)
(use RFeature)
(use Extra)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm330 0
)

(local
	local0
	tavernDoor
	crusherShutDoor
	workShopDoor
	crusher
)
(procedure (GoneFishing)
	(HighPrint 330 0)
	;There is a sign on the door which says: "Gone Fishing".
)

(instance rm330 of Room
	(properties
		picture 330
		east 320
	)
	
	(method (init)
		(LoadMany VIEW vTownOutlook vTownBarOutside vEgoTired)
		(if (Btst TAVERN_DRUNK) (Load VIEW vEgoDefeated))
		(if (Btst TAVERN_THROWN_OUT) (Load VIEW vEgoDefeated) (Load VIEW vCrusher))
		(rm330
			style:
				(switch prevRoomNum
					(0 WIPELEFT)
					(320 WIPELEFT)
					(331 16)
					(332 16)
					(else  HSHUTTER)
				)
		)
		(super init:)
		(mouseDownHandler add: self)
		(self
			setFeatures:
				onWorkShop
				onTavern
				onAlley
				onButcherShop
				onBakery
				onButcherNote
				onBakeryNote
		)
		(StatusLine enable:)
		(self setLocales: STREET TOWN)
		(if (and (not (Btst TAVERN_THROWN_OUT)) (not (Btst TAVERN_DRUNK)))
			(NormalEgo)
			(ego init:)
		else
			(ego view: vEgoDefeated init: hide:)
		)
		(if (not (Btst TAVERN_THROWN_OUT))
			((= tavernDoor (Door new:))
				view: vTownBarOutside
				loop: 0
				cel: 0
				posn: 93 79
				doorControl: cYELLOW
				locked: (if (> timeODay TIME_MIDNIGHT) TRUE else FALSE)
				entranceTo: 331
				facingLoop: 3
				init:
				setPri: 9
				stopUpd:
			)
		)
		((View new:)
			view: vTownBarOutside
			posn: 110 74
			loop: 2
			cel: 1
			setPri: 9
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vTownBarOutside
			posn: 297 65
			loop: 2
			cel: 2
			setPri: 3
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: vTownBarOutside
			posn: 189 84
			loop: 2
			cel: 0
			setPri: 11
			init:
			stopUpd:
			addToPic:
		)
		(cond 
			((== prevRoomNum 0) (ego posn: 318 174 setMotion: MoveTo 300 174))
			((== prevRoomNum 64) (ego posn: 63 168 setMotion: MoveTo 123 168))
			((== prevRoomNum 320) (ego posn: 318 174 setMotion: MoveTo 300 174))
			((or (== prevRoomNum 331) (== prevRoomNum 332))
				(cond 
					((Btst TAVERN_THROWN_OUT) (self setScript: kickOutScript))
					((Btst TAVERN_DRUNK)
						(= [invNum iSilver] 0)
						(= [invNum iGold] 0)
						(tavernDoor cel: 0 doorState: 0)
						(self setScript: gotDrunkScript)
					)
					(else (ego loop: 2 posn: 112 133) (tavernDoor close:))
				)
			)
			((== prevRoomNum 999)
				(ego
					view: vEgoTired
					setLoop: 3
					cel: 5
					init:
					setScript: egoWakes
				)
			)
			(else (ego loop: 2 posn: 148 135 setMotion: MoveTo 148 144))
		)
		(if (not Night)
			((View new:)
				view: vTownOutlook
				posn: 205 120
				loop: 2
				cel: 3
				init:
				stopUpd:
				addToPic:
			)
			((View new:)
				view: vTownOutlook
				posn: 304 117
				loop: 2
				cel: 3
				init:
				stopUpd:
				addToPic:
			)
		else
			((Extra new:)
				view: vTownBarOutside
				setLoop: 3
				ignoreActors: 1
				posn: 160 126
				setPri: 7
				cycleType: 1
				pauseCel: 1
				minCycles: 1
				maxCycles: 1
				cycleSpeed: 0
				minPause: 12
				maxPause: 30
				init:
			)
		)
		;EO: The door is actually animated. Was the workshop going to be accessible at one point?
		((= workShopDoor (Prop new:)) 
			view: vTownBarOutside
			loop: 1
			cel: 3
			posn: 3 187
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
	)
	
	(method (doit)
		(if (== (ego onControl: origin) cLCYAN)
			(if Night
				(curRoom newRoom: 334)
			else
				(curRoom newRoom: 333)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(Bset fBeenIn330)
		(cond 
			((Btst TAVERN_THROWN_OUT) (DisposeScript JUMP) (Bclr TAVERN_THROWN_OUT))
			((Btst TAVERN_DRUNK) (Bclr TAVERN_DRUNK))
		)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'open/door')
						(cond 
							((== (ego onControl: origin) cYELLOW)
								(if (> timeODay TIME_MIDNIGHT)
									(HighPrint 330 4)
									;The door appears to be locked.
								else
									(HighPrint 330 5)
									;Turn around.
								)
							)
							(
								(or
									(== (ego onControl: origin) cLRED)
									(== (ego onControl: origin) cLMAGENTA)
								)
								(HighPrint 330 4)
								;The door appears to be locked.
								(if (not Night) (GoneFishing))
							)
							((== (ego onControl: origin) cLGREEN)
								(HighPrint 330 4)
								;The door appears to be locked.
								)
							(else (event claimed: FALSE))
						)
					)
					((Said 'look,read/sign,note')
						(if
							(and
								(or
									(> (ego x?) 230)
									(and (> (ego x?) 148) (< (ego x?) 230))
								)
								(not Night)
							)
							(GoneFishing)
						else
							(HighPrint 330 6)
							;There are several signs above the various establishments on this street, indicating the nature of their business.
						)
					)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/!*,street,hamlet]')
								(HighPrint 330 7)
								;You are in the northwest corner of town. There are a Butcher's Shop and a Bakery on the north side of the street.
								;Across from them are the backs of buildings on the next street over.
								(HighPrint 330 8)
								;The Tavern in the corner looks rather rundown, and the alley beside it is dark.
								;On the other side of the tavern is a rather practical-looking building, like a workshop.
								)
							((Said '/bakery,shop[<baker]')
								(HighPrint 330 9)
								;The bakery seems to be closed, although there are some dried-up-looking cupcakes in the window.
								;
								(if (not Night) (GoneFishing))
							)
							((Said '/butcher,(shop[<butcher])')
								(HighPrint 330 10)
								;The Butcher's shop seems to be closed.
								)
							((Said '/shop')
								(HighPrint 330 11)
								;Which shop do you mean?
								)
							((Said '/tavern,bar')
								(HighPrint 330 12)
								;The building looks old, dark, and a little seedy.
								)
							((Said '/shed,shed')
								(if Night
									(HighPrint 330 13)
									;The workshop is securely locked.
								else
									(HighPrint 330 14)
									;You can hear sounds of hammering from inside.  The door is securely locked, and no one responds to your knock.
								)
							)
							((Said '/door')
								(cond 
									(
										(or
											(> (ego x?) 230)
											(and (> (ego x?) 148) (< (ego x?) 230))
										)
										(if (not Night)
											(GoneFishing)
										else
											(event claimed: 0)
										)
									)
									((and (< 64 (ego x?)) (< (ego x?) 148))
										(HighPrint 330 15)
										;Looks like the door to a Tavern.
										)
									((and (< 0 (ego x?)) (< (ego x?) 64))
										(HighPrint 330 16)
										;Looks like this door has been closed for a long time.
										)
									(else
										(HighPrint 330 17)
										;There are signs affixed to the door knobs.
										)
								)
							)
							((Said '/alley')
								(if Night
									(HighPrint 330 18)
									;It's very dark, but you can see something shining on the ground at the far end of the alley.
								else
									(HighPrint 330 19)
									;It's dark, but there seems to be someone in there.
								)
							)
							((Said '/window')
								(cond 
									((> (ego x?) 230)
										(HighPrint 330 20)
										;Other than the sorry-looking goods in the window, it is too dark to make out much of anything.
										(if (not Night) (GoneFishing))
									)
									((and (> (ego x?) 148) (< (ego x?) 230))
										(HighPrint 330 21)
										;The Butcher's shop seems to be closed.
										;Through the window, you see what appears to be a layer of dust over most of the interior.
										(if (not Night) (GoneFishing))
									)
									(else
										(HighPrint 330 22)
										;You can't see into any windows from where you're standing.
										)
								)
							)
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
	
	(method (notify param1)
		(cond 
			((!= param1 1))
			((not (tavernDoor locked?))
				(HighPrint 330 1)
				;The door isn't locked.  Just turn around.
				)
			;CI: NOTE: is there a time when the Tavern door is locked?
			;EO: Yes; when "It is not yet dawn". Despite this, the lock can be picked easily,
			;and when you enter, nothing is different.
			((not (TrySkill PICK tryPickTavern lockPickBonus))
				(HighPrint 330 2)
				;You haven't been practicing very much.
				)
			(else (tavernDoor locked: FALSE)
				(HighPrint 330 3)
				;That was the easiest lock you've ever encountered.  It's now unlocked.
				)
		)
	)
)

(instance kickOutScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setLoop: 2
					setCel: 0
					illegalBits: 0
					posn: 123 114
					ignoreActors:
					setPri: 8
					show:
				)
				((= crusher (View new:))
					view: vCrusher
					loop: 1
					cel: 3
					posn: 120 126
					ignoreActors:
					init:
					setPri: 7
					stopUpd:
				)
				((= crusherShutDoor (Prop new:))
					view: vTownBarOutside
					loop: 0
					cel: 0
					posn: 93 79
					init:
					ignoreActors:
					setPri: 9
					stopUpd:
				)
				(HandsOff)
				(= cycles 5)
			)
			(1
				(crusherShutDoor setCycle: EndLoop)
				(= cycles 5)
			)
			(2
				(crusherShutDoor stopUpd:)
				(ego setPri: 11 setMotion: JumpTo 74 166 self)
			)
			(3
				(ego setCel: -1 setCycle: EndLoop self)
			)
			(4
				(ShakeScreen 5)
				(crusher dispose:)
				(crusherShutDoor dispose:)
				((= tavernDoor (Door new:))
					view: vTownBarOutside
					loop: 0
					cel: 0
					posn: 93 79
					doorControl: cYELLOW
					locked: (if (> timeODay TIME_MIDNIGHT) TRUE else FALSE)
					entranceTo: 331
					facingLoop: 3
					doorState: 0
					init:
					setPri: 9
					stopUpd:
				)
				(= seconds 4)
			)
			(5
				(ego
					setLoop: 4
					setCel: 0
					posn: 62 166
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(6
				;EO: Seems intentionally coded to prevent the Hero from dying here.
				(if (not (TakeDamage 5)) (TakeDamage -5))
				(HandsOn)
				(NormalEgo)
				(ego cycleSpeed: 0 loop: 2 cel: 2)
			)
		)
	)
)

(instance gotDrunkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego loop: 2 cel: 2 illegalBits: 0 posn: 74 166 show:)
				(HandsOff)
				(= seconds 7)
			)
			(1
				(HighPrint 330 23)
				;You regain consciousness.
				(ego
					loop: 4
					cel: 0
					posn: 62 166
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(2
				(HighPrint 330 24)
				;After dusting yourself off, you check to make sure you're still in one piece.
				(HighPrint 330 25)
				;Unfortunately, you discover that while you were "out", someone managed to relieve you of all of your money.
				(HighPrint 330 26)
				;Now you'll have to find a way to get some money. The street is not the most comfortable place to sleep, and you have to eat, sooner or later.
				(HandsOn)
				(NormalEgo)
				(ego cycleSpeed: 0 loop: 2 cel: 2)
			)
		)
	)
)

(instance onWorkShop of RFeature
	(properties
		nsTop 59
		nsBottom 165
		nsRight 64
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onWorkShop event shiftDown)
				(if Night
					(HighPrint 330 13)
					;The workshop is securely locked.
				else
					(HighPrint 330 27)
					;You can hear sounds of hammering from inside.  It must be some sort of workshop.
				)
			)
		)
	)
)

(instance onTavern of RFeature
	(properties
		nsTop 8
		nsLeft 65
		nsBottom 128
		nsRight 132
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onTavern event shiftDown)
				(HighPrint 330 28)
				;The Tavern in the corner looks rather rundown.
			)
		)
	)
)

(instance onAlley of RFeature
	(properties
		nsTop 12
		nsLeft 133
		nsBottom 133
		nsRight 164
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onAlley event shiftDown)
				(if Night
					(HighPrint 330 18)
					;It's very dark, but you can see something shining on the ground at the far end of the alley.
				else
					(HighPrint 330 19)
					;It's dark, but there seems to be someone in there.
				)
			)
		)
	)
)

(instance onButcherShop of RFeature
	(properties
		nsTop 15
		nsLeft 165
		nsBottom 142
		nsRight 238
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onButcherNote event shiftDown)
				(cond 
					(
						(and
							(> (ego x?) 148)
							(< (ego x?) 230)
							(not Night)
						)
						(GoneFishing)
					)
					((not Night) (NotClose))
					(else
						(HighPrint 330 10)
						;The Butcher's shop seems to be closed.
						)
				)
			)
			((MouseClaimed onButcherShop event shiftDown)
				(HighPrint 330 10)
				;The Butcher's shop seems to be closed.
				)
		)
	)
)

(instance onBakery of RFeature
	(properties
		nsTop 7
		nsLeft 261
		nsBottom 140
		nsRight MAXRIGHT
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((MouseClaimed onBakeryNote event shiftDown)
				(cond 
					((and (> (ego x?) 230) (not Night)) (GoneFishing))
					((not Night) (NotClose))
					(else
						(HighPrint 330 9)
						;The bakery seems to be closed, although there are some dried-up-looking cupcakes in the window.
						)
				)
			)
			((MouseClaimed onBakery event shiftDown)
				(HighPrint 330 9)
				;The bakery seems to be closed, although there are some dried-up-looking cupcakes in the window.
				)
		)
	)
)

(instance onButcherNote of RFeature
	(properties
		nsTop 115
		nsLeft 201
		nsBottom 122
		nsRight 208
	)
)

(instance onBakeryNote of RFeature
	(properties
		nsTop 112
		nsLeft 300
		nsBottom 119
		nsRight 308
	)
)

(instance egoWakes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(1
				(NormalEgo)
				(HighPrint 330 29)
				;However, this was not a good place to sleep.  You're stiff and sore all over and your wallet feels very light.
				(= [invNum iSilver] 0)
				(= [invNum iGold] 0)
				(HandsOn)
			)
		)
	)
)
