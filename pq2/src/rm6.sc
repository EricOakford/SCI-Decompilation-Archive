;;; Sierra Script 1.0 - (do not remove this comment)
(script# 6)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm6 0
)
(synonyms
	(cop detective)
)

(local
	local0
	numPeopleInRoom
)
(instance James of Feature
	(method (handleEvent event)
		(cond
			((or (event claimed:) (!= (event type:) saidEvent))
				(return)
			)
			(
				(not
					(or
						(ego inRect: 124 128 192 165)
						(and
							(ego inRect: 193 145 240 154)
							(== (ego loop:) 1)
						)
					)
				)
				(if (not (Said '/james'))
					(return)
				else
					(NotClose) ; "You're not close enough."
				)
			)
			((Said 'look/desk')
				(Print 6 0) ; "This is the desk where detective James Simpson does his creative report writing."
			)
			((Said 'look/james,simpson,man,cop')
				(Print 6 1) ; "James is one cool dude."
			)
			((or (Said 'talk/james,simpson,man,cop') (Said 'hello'))
				(if (== global172 1)
					(Print 6 2) ; ""What can I do for you ?" says Detective Simpson."
				else
					(switch (Random 0 2)
						(0
							(Print 6 3) ; "Detective James Simpson says, "What a rat race burglary division is becoming. Someday, maybe, I'll be working homicide.""
						)
						(1
							(Print 6 4) ; "Bonds," says Simpson, "this ain't the homicide office."
						)
						(2
							(Print 6 5) ; "Chuckling, Detective Simpson says to you, "Hey Bonds, you should have been with us on the big caper last night.""
							(Print 6 6) ; "Simpson continues, "We made a pinch on this squirrelly pecker wood who was raiding clothes lines over at the college dorm. He had in his possession, 15 jockstraps, 23 pair of panties and one paper bag over his head !""
						)
					)
				)
			)
			((Said 'talk,ask>')
				(cond
					((not global172)
						(event claimed: TRUE)
						(Print 6 7) ; "I don't know what you are talking about. Go ask somebody else."
					)
					(
						(or
							(Said '/browning,burglary')
							(Said '//browning,burglary')
						)
						(Print 6 8) ; "Oh yes," says Detective Simpson, "we just worked a recent pawn shop burglary."
						(Print 6 9) ; "Simpson continues, "Taken in that burglary were two shotguns, a 32 automatic with silencer, and ammunition.""
						(SolvePuzzle 2 92)
						(++ local0)
					)
					(
						(or
							(Said '/print,finger,fingerprint,thumb')
							(Said '//print,finger,fingerprint,thumb')
						)
						(Print 6 10) ; "Affirmative on the prints," says Jim, "we dusted everything. Bains' prints were all over that shotgun."
						(SolvePuzzle 2 93)
						(++ local0)
					)
					((or (Said '/number<serial') (Said '//number<serial'))
						(Print 6 11) ; "Checking his notes he says, "The number of the 32 auto is BHG5556947. The numbers on the two shotguns are BSG12P3170 and WSGA122372.""
					)
					((Said '/*')
						(if (>= local0 6)
							(Print 6 12) ; "James says, "I have a lot of work to do Bonds.""
						else
							(Print 6 2) ; ""What can I do for you ?" says Detective Simpson."
						)
					)
				)
			)
		)
	)
)

(instance William of Feature
	(method (handleEvent event)
		(cond
			((or (event claimed:) (!= (event type:) saidEvent))
				(return)
			)
			(
				(not
					(or
						(ego inRect: 190 128 239 146)
						(and
							(ego inRect: 193 145 240 154)
							(== (ego loop:) 3)
						)
					)
				)
				(cond
					((not (Said '/cole,willie,jerome'))
						(return)
					)
					((== numPeopleInRoom 0)
						(Print 6 13) ; "Detective Jerome is not here right now."
					)
					(else
						(NotClose) ; "You're not close enough."
					)
				)
			)
			((Said 'look/desk')
				(Print 6 14) ; "This is the desk of detective William Jerome."
			)
			((Said 'ask')
				(if (== numPeopleInRoom 0)
					(Print 6 13) ; "Detective Jerome is not here right now."
				else
					(Print 6 15) ; "I'm sorry Sonny, but I can't help you with that."
				)
			)
			((or (Said '/cole,willie,jerome,man,cop>') (Said 'hello>'))
				(cond
					((== numPeopleInRoom 0)
						(event claimed: TRUE)
						(Print 6 13) ; "Detective Jerome is not here right now."
					)
					((Said 'look')
						(Print 6 16) ; "Willie is working industriously."
					)
					((or (Said 'talk') (Said 'hello'))
						(switch (Random 0 4)
							(0
								(Print 6 17) ; ""Sonny my man," says Detective William 'Willie' Jerome."
							)
							(1
								(Print 6 18) ; ""Ain't no murder suspects in here!" says Willie."
							)
							(2
								(Print 6 19) ; "Detective Jerome says, "What are you looking for?""
							)
							(3
								(Print 6 20) ; ""Homicide's down the hall son !" Jerome says."
							)
							(4
								(Print 6 21) ; "Well!" says Willie, "if it ain't the #1 homicide dick of Lytton."
							)
						)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
		)
	)
)

(instance Robert of Feature
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
				)
				(return)
			)
			((not (ego inRect: 56 120 120 146))
				(cond 
					((not (Said '/lieutenant,bob,adams'))
						(return)
					)
					((<= numPeopleInRoom 1)
						(Print 6 22)
					)
					(else
						(NotClose)
					)
				)
			)
			((Said 'look/desk')
				(Print 6 23)
			)
			((Said 'ask')
				(if (<= numPeopleInRoom 1)
					(Print 6 22)
				else
					(Print 6 24)
				)
			)
			(
				(or
					(Said '/lieutenant,bob,adams,dude,cop>')
					(Said 'hello>')
				)
				(cond 
					((<= numPeopleInRoom 1)
						(event claimed: TRUE)
						(Print 6 22)
					)
					((Said 'look')
						(Print 6 25)
					)
					(
						(or
							(Said 'chat')
							(Said 'hello')
						)
						(switch (Random 0 2)
							(0
								(Print 6 26)
							)
							(1
								(Print 6 27)
							)
							(2
								(Print 6 28)
							)
						)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((Said '/affirmative')
				(Print 6 29)
			)
		)
	)
)

(instance Laura of Feature
	(method (handleEvent event)
		(cond
			((or (event claimed:) (!= (event type:) saidEvent))
				(return)
			)
			(
				(not
					(or
						(ego inRect: 137 116 300 129)
						(and
							(ego inRect: 137 116 300 135)
							(== (ego loop:) 3)
						)
					)
				)
				(cond
					((not (Said '/holt,woman'))
						(return)
					)
					((> numPeopleInRoom 2)
						(Print 6 30) ; "Detective Gomez is not here right now."
					)
					(else
						(NotClose) ; "You're not close enough."
					)
				)
			)
			((Said 'look/desk')
				(Print 6 31) ; "When she's here, Burglary Detective Laura Gomez claims this desk."
			)
			((Said 'kiss')
				(Print 6 32) ; "No thanks, my husband may not like it."
			)
			((Said 'ask')
				(if (> numPeopleInRoom 2)
					(Print 6 30) ; "Detective Gomez is not here right now."
				else
					(Print 6 33) ; "I'm sorry, but I can't help you with that."
				)
			)
			((or (Said '/holt,woman,cop>') (Said 'hello>'))
				(cond
					((> numPeopleInRoom 2)
						(event claimed: TRUE)
						(Print 6 30) ; "Detective Gomez is not here right now."
					)
					((Said 'look')
						(Print 6 34) ; "Laura works eagerly away on her current case."
					)
					((or (Said 'talk') (Said 'hello'))
						(switch (Random 0 2)
							(0
								(Print 6 35) ; "Hi, Sonny," says Laura, "long time no see."
							)
							(1
								(Print 6 36) ; "Detective Laura Gomez comments, "Sure is nice to be out of Traffic Division and into Burglary for a change.""
							)
							(2
								(Print 6 37) ; "Laura says, "Have you seen Marie lately? What a sweet girl she turned out to be.""
							)
						)
					)
					((Said 'kiss,pinch,fuck')
						(switch (Random 0 1)
							(0
								(Print 6 38) ; "In an angry tone of voice, Laura says, "Why, you crap house RAT! I oughta slap your eye balls out!""
							)
							(1
								(Print 6 39) ; "Detective Gomez verbally assaults you, "Listen gutter mouth!" she says, "the only reason I don't drop kick-you over this desk right now is because, no doubt, the obvious lack of oxygen to your brain has apparently rendered you a mental defect!""
							)
						)
					)
				)
			)
		)
	)
)

(instance Computer of Feature
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
				)
				(return)
			)
			((not (ego inRect: 193 145 240 154))
				(if (Said '/computer')
					(NotClose)
				else
					(return)
				)
			)
			((Said 'look/desk')
				(Print 6 40)
			)
			((Said 'turn<on/computer')
				(Print 6 41)
			)
			((Said 'look,use/computer')
				(curRoom newRoom: 8)
			)
		)
	)
)

(instance rm6 of Room
	(properties
		picture 6
		style WIPEDOWN
	)
	
	(method (init)
		(Load VIEW 1)
		(Load VIEW 68)
		(super init:)
		(self setFeatures: Laura James William Robert Computer)
		(self setLocales: regFieldKit regOffice)
		(HandsOn)
		(= local0 0)
		(= numPeopleInRoom (Random 0 4))
		(= gunFireState gunPROHIBITED)
		(if (!= prevRoomNum 8)
			(User prevDir: 1)
			(ego
				posn: 87 158
				setMotion: MoveTo 87 10
			)
		)
		(ego
			view: 1
			setCycle: Walk
			illegalBits: cWHITE ;-32768
			init:
		)
		(if (<= numPeopleInRoom 2)
			((View new:) ;laura
				view: 68
				posn: 185 125
				loop: 0
				cel: 0
				init:
				addToPic:
			)
		)
		(if (!= numPeopleInRoom 0)
			((View new:) ;William 
				view: 68
				posn: 206 142
				loop: 0
				cel: 1
				init:
				addToPic:
			)
		)
		((View new:) ;James
			view: 68
			posn: 182 148
			loop: 0
			cel: 3
			init:
			addToPic:
		)
		(if (> numPeopleInRoom 1)
			((View new:) ;Adams
				view: 68
				posn: 92 112
				loop: 0
				cel: 2
				init:
				addToPic:
			)
		)
		(self setScript: rm6Script)
	)
	
	(method (dispose)
		(features eachElementDo: #dispose #delete)
		(super dispose:)
	)
)

(instance rm6Script of Script
	(properties)
	
	(method (doit)
		(if (> (ego y?) 160)
			(curRoom newRoom: 2)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '/flyer,painting')
								(Print 6 42)
							)
							((Said '/wastebasket,garbage')
								(Print 6 43)
							)
							((Said '[<at,around][/(!*,chamber,office)]')
								(Print 6 44)
							)
						)
					)
					((Said 'empty,clean[/newspaper,garbage,basket]')
						(Print 6 45)
					)
				)
			)
		)
	)
)
