;;; Sierra Script 1.0 - (do not remove this comment)
(script# 45)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room45 0
)
(synonyms
	(room room castle)
	(cat cat cat)
)

(local
	fairy1
	fairy2
	candleLight1
	candleLight2
	cat
	printObj
	genesta
	genestaCloseup
	genestaFace
	poof
	[local10 2]
)
(instance Room45 of Room
	(properties
		picture 45
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 632)
		(Load VIEW 645)
		(Load VIEW 365)
		(Load VIEW 101)
		(Load VIEW 112)
		(Load VIEW 115)
		(super init:)
		(HandsOn)
		(= inCutscene FALSE)
		(fairyMusic play:)
		(ego
			view: 4
			posn: 225 174
			illegalBits: cWHITE
			init:
			setStep: 4 2
			setPri: -1
		)
		(if howFast
			((View new:) view: 632 posn: 115 100 setPri: 0 addToPic:)
			((View new:) view: 632 posn: 201 100 setPri: 0 addToPic:)
			((= candleLight1 (Prop new:))
				view: 632
				posn: 115 43
				setLoop: 1
				setCycle: Forward
				init:
			)
			((= candleLight2 (Prop new:))
				view: 632
				posn: 201 43
				setLoop: 1
				setCycle: Forward
				init:
			)
		)
		((= genesta (Prop new:))
			view: 101
			loop: 0
			cel: 0
			ignoreActors: TRUE
			posn: 159 126
			setPri: 11
			init:
		)
		((= fairy1 (Actor new:))
			view: 112
			posn: 110 60
			setStep: 1 59
			setLoop: 0
			setCycle: Forward
			setScript: Flutter3
			init:
		)
		((= fairy2 (Actor new:))
			view: 115
			posn: 200 59
			setStep: 1 1
			setLoop: 1
			setCycle: Forward
			setScript: Flutter6
			init:
		)
		((= cat (Prop new:))
			view: 365
			posn: 59 130
			ignoreActors: TRUE
			init:
		)
		(if howFast
			(cat setScript: catMove)
		else
			(cat addToPic:)
		)
		(ego observeBlocks: catBlock)
	)
	
	(method (doit)
		(super doit:)
		(if (cast contains: ego)
			(if (& (ego onControl: 0) cBROWN)
				(curRoom newRoom: 46)
			)
			(if
				(and
					(ego inRect: 93 116 223 151)
					(!= (self script?) closer)
				)
				(cond 
					((< (ego x?) 158)
						(if (!= (fairy1 script?) ChaseEgo)
							(fairy1 setScript: ChaseEgo)
						)
					)
					((!= (fairy2 script?) ChaseEgo)
						(fairy2 setScript: ChaseEgo)
					)
				)
			else
				(if (!= (fairy1 script?) ChaseEgoWhenTalk)
					(fairy1 setScript: Flutter3)
				)
				(if (!= (fairy2 script?) ChaseEgoWhenTalk)
					(fairy2 setScript: Flutter6)
				)
			)
		)
	)
	
	(method (dispose)
		(cls)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((Said 'look>')
							(cond 
								((Said '[<on,in,at]/bed')
									(Print 45 0)
								)
								((Said '<under/bed')
									(Print 45 1)
								)
								((Said '/wand[<magic]')
									(Print 45 2)
								)
								((Said '/window')
									(if (ego inRect: 113 110 208 122)
										(Print 45 3)
									else
										(Print 45 4)
									)
								)
								((Said '/blossom,flora')
									(Print 45 5)
								)
								((Said '/stair')
									(Print 45 6)
								)
								((Said '/chest,dresser,drawer>')
									(if (Said '<in,in')
										(Print 45 7)
									else
										(Print 45 8)
										(event claimed: TRUE)
									)
								)
								((Said '/wall')
									(Print 45 9)
								)
								((Said '[<down]/dirt')
									(Print 45 10)
								)
								((Said '/genesta')
									(Print
										(Format @str 45 11
											(if (== lolotteAlive TRUE)
												{This will be a loss for Tamir, as the evil Lolotte will come to power!}
											else
												{_}
											)
										)
									)
								)
								((Said '/cat[<snow,white]')
									(Print 45 12)
								)
								((Said '[<around][/room,tower,bedroom,chamber[<bed]]')
									(Print 45 13)
								)
								(else
									(event claimed: FALSE)
								)
							)
						)
						((Said 'deliver,return/amulet[/genesta]')
							(if (ego has: iTalisman)
								(cls)
								(HandsOff)
								(= gameHours 30)
								(= gameMinutes 1)
								(ego put: iTalisman 999)
								(fairyMusic dispose:)
								(Print 45 14 #at 0 10 #font smallFont)
								(= inCinematic TRUE) ;bring on the endgame
								(self setScript: closer)
							else
								(Print 800 2)
							)
						)
						(
							(or
								(Said '(lay<down,on,in),(sleep<in,on),(get<in,on)[/bed]')
								(Said 'sleep[/noword]')
							)
							(Print 45 15)
						)
						((Said 'get/blossom')
							(Print 45 16)
						)
						((Said 'get,get,rob/wand')
							(Print 45 17)
						)
						((Said 'open/dresser,drawer,chest') (Print 45 7))
						(
						(or (Said 'converse/genesta') (Said 'converse[/noword]'))
							(Print 45 18)
							(cond 
								((< (ego x?) 158)
									(if (== (fairy1 script?) Flutter3)
										(fairy1 setScript: ChaseEgoWhenTalk)
									)
								)
								((== (fairy2 script?) Flutter6)
									(fairy2 setScript: ChaseEgoWhenTalk)
								)
							)
						)
						((Said 'kill/genesta')
							(Print 45 19)
						)
						((Said 'get/genesta')
							(Print 45 20)
						)
						((or (Said 'kiss/genesta') (Said 'kiss[/noword]'))
							(Print 45 21)
						)
						((Said 'help,,/genesta')
							(Print 45 22)
						)
						(else
							(event claimed: FALSE)
						)
					)
					(if (not (event claimed?))
						(cond 
							((Said 'look[/fairies[<little]]')
								(Print 45 23)
							)
							((Said 'converse[/fairies]')
								(Print 45 24)
							)
							((Said 'kill[/fairies]')
								(Print 45 25)
							)
							((Said 'get/fairies')
								(Print 45 26)
							)
							((Said 'capture/fairies')
								(Print 45 26)
							)
							((Said 'kiss/fairies')
								(Print 45 27)
							)
							((Said 'help/fairies')
								(Print 45 28)
							)
							((Said 'converse/cat[<snow,white]')
								(Print 45 29)
							)
							((Said 'kill/cat[<snow,white]')
								(Print 45 30)
							)
							((Said 'get,capture/cat[<snow,white]')
								(Print 45 31)
							)
							((Said 'kiss,pat/cat[<snow,white]')
								(Print 45 32)
							)
							(
								(or
									(Said 'deliver/cat/anyword')
									(Said 'deliver/anyword/cat')
								)
								(Print 45 33)
							)
							(
								(or
									(Said 'deliver/fairies/anyword')
									(Said 'deliver/anyword/fairies')
								)
								(Print 45 34)
							)
							(
								(or
									(Said 'deliver/genesta,fairies/anyword')
									(Said 'deliver/anyword[/genesta,fairies]')
								)
								(Print 45 35)
							)
						)
					)
				)
			)
		)
	)
)

(instance ChaseEgo of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 45 36 #at -1 10)
				(client
					yStep: 2
					illegalBits: 0
					setMotion: Chase ego 30 self
				)
			)
			(1
				(client
					yStep: 1
					setMotion: MoveTo (ego x?) (- (ego y?) 55) self
				)
			)
		)
	)
)

(instance ChaseEgoWhenTalk of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					illegalBits: 0
					yStep: 2
					setMotion: MoveTo (ego x?) (+ (client y?) 25)
				)
				(= seconds 4)
			)
			(1
				(client setScript: 0)
			)
		)
	)
)

(instance Flutter3 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client illegalBits: 0 setMotion: MoveTo 103 60 self)
			)
			(1
				(client illegalBits: 8192 setMotion: Wander 5)
			)
		)
	)
)

(instance Flutter6 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client illegalBits: 0 setMotion: MoveTo 220 60 self)
			)
			(1
				(client illegalBits: 8192 setMotion: Wander 5)
			)
		)
	)
)

(instance catMove of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
			)
			(1
				(cat setCycle: EndLoop self)
			)
			(2
				(if (> (Random 0 200) 180)
					(cat
						cycleSpeed: 1
						setLoop: 1
						cel: 255
						setCycle: EndLoop self
					)
				else
					(self cue:)
				)
			)
			(3
				(cat stopUpd:)
				(= seconds 3)
			)
			(4
				(cat setLoop: 0)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance closer of Script
	(method (init who)
		(Load VIEW 101)
		(Load VIEW 103)
		(Load VIEW 104)
		(super init: who)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= isHandsOff FALSE)
				(cls)
				(theGame changeScore: 10)
				(cast eachElementDo: #hide)
				(DrawPic 991)
				(genesta view: 103 loop: 0 posn: 160 159 show:)
				((= genestaCloseup (Prop new:))
					view: 103
					loop: 1
					posn: (genesta x?) (- (genesta y?) 30)
					setPri: (+ (genesta priority?) 1)
					init:
				)
				((= genestaFace (Prop new:))
					view: 103
					loop: 2
					posn: (genesta x?) (genesta y?)
					setPri: (+ (genesta priority?) 1)
					init:
					setCycle: Forward
				)
				(= seconds 4)
			)
			(1
				(genestaCloseup cycleSpeed: 2 setCycle: EndLoop)
				(= seconds 4)
			)
			(2
				(genestaFace dispose:)
				(genestaCloseup dispose:)
				(genesta hide:)
				(= cycles 1)
			)
			(3
				(DrawPic 45 IRISOUT)
				((View new:) view: 632 posn: 115 100 setPri: 0 addToPic:)
				((View new:) view: 632 posn: 201 100 setPri: 0 addToPic:)
				(genesta
					view: 101
					loop: 1
					cel: 0
					ignoreActors: TRUE
					posn: 159 126
					setPri: 11
				)
				(cast eachElementDo: #show)
				(genesta setCycle: EndLoop)
				(= seconds 4)
			)
			(4
				(Print 45 37 #at 20 10)
				(Print 45 38 #at 100 140)
				(genesta loop: 2 setCycle: EndLoop self)
			)
			(5
				(Print 45 39 #at -1 150)
				(= printObj (Print 45 40 #at -1 10 #dispose))
				(genesta loop: 3 setCycle: Forward)
				((= poof (Prop new:))
					view: 686
					ignoreActors: 1
					posn: (ego x?) (ego y?)
					setPri: (+ (ego priority?) 1)
					init:
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(6
				(ego dispose:)
				(poof cel: 5 setCycle: EndLoop self)
			)
			(7
				(poof dispose:)
				(cls)
				(genesta view: 104 loop: 1 setCycle: EndLoop self)
			)
			(8
				(= inCutscene TRUE)
				(curRoom newRoom: 690)
			)
		)
	)
)

(instance fairyMusic of Sound
	(properties
		number 34
	)
)

(instance catBlock of Block
	(properties
		top 125
		left 31
		bottom 131
		right 80
	)
)
