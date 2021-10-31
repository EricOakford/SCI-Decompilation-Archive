;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm3 0
)
(synonyms
	(steve jones)
	(cop detective)
	(dooley lieutenant)
)

(local
	talkedToSteve
	talkedToLloyd
	colbyFile
	dickeyFile
	jonesFile
	simmsFile
	sniderFile
	snowFile
	valanciaFile
	drawerLabel
	selectedFile
	theFile
	readingFile
)
(procedure (localproc_1132)
	(switch theFile
		(0
			(mugShot
				loop: 2
				cel: 0
				init:
			)
			(RedrawCast)
			(Display 3 67
				p_at 120 10
				p_width 180
				p_font 0
			)
			(Display 3 68
				p_at 20 65
				p_width 280
			)
		)
		(1
			(mugShot
				loop: 1
				cel: 2
				init:
			)
			(RedrawCast)
			(Display 3 69
				p_at 120 10
				p_width 180
				p_font 0
			)
			(Display 3 70
				p_at 20 65
				p_width 300
			)
		)
		(2
			(mugShot
				loop: 0
				cel: 2
				init:)
			(RedrawCast)
			(Display 3 71
				p_at 120 10
				p_width 180
				p_font 0)
			(Display 3 72
				p_at 20 65
				p_width 30
			)
		)
		(3
			(mugShot
				loop: 0
				cel: 0
				init:
			)
			(RedrawCast)
			(Display 3 73
				p_at 120 10
				p_width 180
				p_font 0
			)
			(Display 3 74
				p_at 20 65
				p_width 300
			)
		)
		(4
			(mugShot
				loop: 0
				cel: 1
				init:
			)
			(RedrawCast)
			(Display 3 75
				p_at 120 10
				p_width 200
			)
			(Display 3 76
				p_at 20 65
				p_width 300
			)
		)
		(5
			(mugShot
				loop: 1
				cel: 0
				init:
			)
			(RedrawCast)
			(Display 3 77
				p_at 120 10
				p_width 200
			)
			(Display 3 78
				p_at 20 65
				p_width 300
			)
		)
		(6
			(mugShot
				loop: 1
				cel: 1
				init:
			)
			(RedrawCast)
			(Display 3 79
				p_at 120 10
				p_width 180
				p_font 0
			)
			(Display 3 80
				p_at 20 65
				p_width 300
			)
		)
	)
)

(instance Kim of Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
					(!= (curRoom script?) rm3Script)
				)
				(return)
			)
			((not (ego inRect: 100 134 141 156))
				(if (Said '/(kim,broad)')
					(NotClose)
				else
					(event claimed: 0)
				)
			)
			((Said 'look/desk')
				(Print 3 0)
			)
			((Said 'look/kim,broad,cop')
				(Print 3 1)
			)
			(
				(or
					(Said 'chat/kim,broad,cop')
					(Said '/hello')
				)
				(switch (Random 0 2)
					(0
						(Print 3 2)
					)
					(1
						(Print 3 3)
					)
					(else
						(Print 3 4)
					)
				)
			)
			((Said '*/kim,broad')
				(switch (Random 0 2)
					(0
						(Print 3 5)
					)
					(1
						(Print 3 6)
					)
					(2
						(Print 3 7)
					)
				)
			)
			((Said '[ask][/help]')
				(switch (Random 0 2)
					(0
						(Print 3 8)
					)
					(1
						(Print 3 9)
					)
					(2
						(Print 3 10)
					)
				)
			)
			((Said 'ask')
				(Print 3 11)
			)
		)
	)
)

(instance Pratt of Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
					(!= (curRoom script?) rm3Script)
				)
				(return)
			)
			((not (ego inRect: 38 130 100 156))
				(if (Said '/lloyd')
					(NotClose)
				else
					(event claimed: 0)
				)
			)
			((Said 'look/desk')
				(Print 3 12)
			)
			((>= gamePhase 8)
				(if (Said '/lloyd,dude,cop')
					(Print 3 13)
				else
					(return)
				)
			)
			((Said 'look/lloyd,dude,cop')
				(Print 3 14)
			)
			(
				(or
					(Said 'help/lloyd,dude')
					(Said 'chat,ask/lloyd,dude/investigation,cocaine,(complaint<cocaine)')
					(Said'chat,ask/investigation,cocaine,(complaint<cocaine)')
				)
				(cond 
					((== lloydInRehab 1)
						(Print 3 15)
					)
					((Btst fLearnedAboutAddiction)
						(Print 3 16)
						(Print 3 17)
						(Print 3 18)
						(Print 3 19)
						(Print 3 20)
						(SolvePuzzle 5 85)
						(= lloydInRehab 1)
					)
					(else
						(Print 3 21)
					)
				)
			)
			((Said 'ask')
				(Print 3 22)
			)
			(
				(or (Said 'chat/lloyd,dude,cop') (Said '/hello'))
				(cond 
					((== lloydInRehab 1) (Print 3 23))
					((not talkedToLloyd)
						(= talkedToLloyd 1)
						(Print 3 24)
						(switch (Random 0 2)
							(0 (Print 3 25))
							(1 (Print 3 26))
							(2 (Print 3 27))
						)
					)
					(else (Print 3 28))
				)
			)
		)
	)
)

(instance Poet of Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
					(!= (curRoom script?) rm3Script)
				)
				(return)
			)
			((not (ego inRect: 168 134 240 156))
				(if (Said '/steve')
					(NotClose)
				else
					(event claimed: 0)
				)
			)
			((Said 'look/desk')
				(Print 3 29)
			)
			((Said 'look/steve,dude,cop')
				(Print 3 30)
			)
			(
				(or
					(Said 'chat,ask/steve,dude,cop')
					(Said '/hello')
				)
				(switch talkedToSteve
					(0
						(Print 3 31)
						(= talkedToSteve 1)
					)
					(1
						(Print 3 32)
					)
					(else
						(Print 3 33)
					)
				)
			)
			(
				(or
					(and (== talkedToSteve 1) (Said 'affirmative'))
					(Said 'ask,tell,listen/poem,steve')
				)
				(switch (Random 0 3)
					(0
						(Print 3 34)
					)
					(1
						(Print 3 35)
					)
					(2
						(Print 3 36)
					)
					(3
						(Print 3 37)
					)
				)
				(= talkedToSteve 2)
			)
			((Said 'ask')
				(Print 3 11)
			)
			((Said 'affirmative')
				(Print 3 38)
			)
			((Said 'no')
				(if (== talkedToSteve 1)
					(Print 3 39)
					(= talkedToSteve 0)
				else
					(Print 3 38)
				)
			)
		)
	)
)

(instance Dooley of Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
					(!= (curRoom script?) rm3Script)
				)
				(return)
			)
			((not (ego inRect: 158 120 220 134))
				(if (Said '/dooley')
					(NotClose)
				else
					(event claimed: 0)
				)
			)
			((Said 'look/desk')
				(Print 3 40)
			)
			((Said 'look/dooley,dude,cop')
				(Print 3 41)
			)
			(
				(or
					(Said 'chat/dooley,dude,cop')
					(Said '/hello')
				)
				(switch (Random 0 2)
					(0
						(Print 3 42)
					)
					(1
						(Print 3 43)
					)
					(else
						(Print 3 44)
					)
				)
			)
			((Said 'ask')
				(Print 3 45)
			)
			((Said 'help')
				(if (Said '/cabinet,file')
					(Print 3 46)
				else
					(Print 3 47)
				)
			)
		)
	)
)

(instance Computer of Feature
	(properties)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(event claimed?)
					(!= (event type?) saidEvent)
					(!= (curRoom script?) rm3Script)
				)
				(return)
			)
			(
				(not
					(if (ego inRect: 75 120 120 130)
					else
						(& (ego onControl:) cYELLOW) ;$e000
					)
				)
				(if (Said '/computer')
					(NotClose)
				else
					(event claimed: 0)
				)
			)
			((Said 'look/desk')
				(Print 3 48)
			)
			((Said 'turn<on/computer')
				(Print 3 49)
			)
			((Said 'look,use/computer')
				(curRoom newRoom: 8)
			)
		)
	)
)

(instance rm3 of Room
	(properties
		picture 3
		style HWIPE
	)
	
	(method (init)
		(Load VIEW 1)
		(Load VIEW 61)
		(super init:)
		(ego
			posn:
				(if (== prevRoomNum 8)120 else 180)
				(if (== prevRoomNum 8) 120 else 172)
			view:
				(cond 
					((== prevRoomNum 8) (if (not gunDrawn) 0 else 6))
					((not gunDrawn) 1)
					(else 7)
				)
		)
		(self setFeatures: Kim Pratt Poet Dooley Computer)
		(self setLocales: 153 156)
		(= gunFireState gunPROHIBITED)
		(HandsOn)
		(curRoom setScript: rm3Script)
	)
	
	(method (dispose)
		(features eachElementDo: #dispose #delete)
		(fileScript dispose:)
		(super dispose:)
	)
)

(instance rm3Script of Script
	(properties)
	
	(method (doit)
		(if (>= (ego y?) 175)
			(curRoom newRoom: 2)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego init:)
				(if (== prevRoomNum 2)
					(ego setMotion: MoveTo 180 10)
					(User prevDir: 1)
				)
				((View new:)
					view: 61
					posn: 222 117
					loop: 0
					cel: 0
					init:
					addToPic:
				)
				((Prop new:)
					view: 61
					posn: 194 122
					loop: 1
					cycleSpeed: 4
					setPri: 11
					setCycle: Forward
					init:
				)
				((View new:)
					view: 61
					posn: 135 146
					loop: 2
					cel: (Random 0 1)
					init:
					addToPic:
				)
				((View new:)
					view: 61
					posn: 178 104
					loop: 6
					cel: 0
					setPri: 9
					init:
					addToPic:
				)
				(if (< gamePhase 8)
					((View new:)
						view: 61
						posn: 61 144
						loop: 3
						cel: (Random 0 1)
						init:
						addToPic:
					)
				)
				(if
					(and
						(>= gamePhase 8)
						(not (Btst fSteveTellsAboutLloyd))
					)
					(Bset fSteveTellsAboutLloyd)
					(if (Btst fLloydInRehab)
						(Print 3 50)
						(Print 3 51)
					else
						(Print 3 52)
						(Print 3 53)
						(Print 3 54)
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said '(look,frisk)/(coat,pocket)')
						(if (ego inRect: 200 150 270 170)
							(Print 3 55)
						else
							(event claimed: 0)
						)
					)
					((Said 'look>')
						(cond 
							((Said '[<at,around][/(!*,chamber,office)]')
								(Print 3 56)
							)
							((Said '/coatrack,coatrack<coat')
								(Print 3 57)
							)
							((Said '/hat')
								(Print 3 58)
							)
						)
					)
					((Said 'get/coat,hat')
						(Print 3 59)
					)
					((Said 'open/drawer,cabinet,file')
						(if
							(or
								(== (ego onControl: 1) cLMAGENTA) ;8192
								(== (ego onControl: 1) -24576)
							)
							(curRoom setScript: fileScript)
						else
							(NotClose)
						)
					)
				)
			)
		)
	)
)

(instance mugShot of View
	(properties
		y 60
		x 75
		view 205
	)
)

(instance fileScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load VIEW 205)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 7 6)
				((= drawerLabel (View new:))
					view: 60
					setPri: 15
					posn: 155 113
					loop: 2
					cel: 1
					stopUpd:
					init:
				)
				((= colbyFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 160 99
					loop: 1
					cel: 0
					setPri: 14
					stopUpd:
					init:
				)
				((= dickeyFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 160 93
					loop: 1
					cel: 1
					setPri: 12
					stopUpd:
					init:
				)
				((= jonesFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 161 87
					loop: 1
					cel: 2
					setPri: 10
					stopUpd:
					init:
				)
				((= simmsFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 160 78
					loop: 1
					cel: 3
					setPri: 8
					stopUpd:
					init:
				)
				((= sniderFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 160 74
					loop: 1
					cel: 4
					setPri: 6
					stopUpd:
					init:
				)
				((= snowFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 161 67
					loop: 1
					cel: 5
					setPri: 4
					stopUpd:
					init:
				)
				((= valanciaFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 161 61
					loop: 1
					cel: 6
					setPri: 2
					stopUpd:
					init:
				)
			)
			(1
				(= readingFile 1)
				(User canInput: 0)
				(if (< howFast 30)
					(self cue:)
				else
					(selectedFile
						setMotion: MoveTo (selectedFile x?) (- (selectedFile y?) 20) self
					)
				)
			)
			(2
				(SetMenu 513 112 0)
				(User canInput: 1)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 90 DISSOLVE)
				(localproc_1132)
			)
			(3
				(User canInput: 0)
				(SetMenu 513 112 1)
				(curRoom drawPic: 7 IRISIN)
				(cast eachElementDo: #show)
				(mugShot dispose:)
				(if (< howFast 30)
					(self cue:)
				else
					(selectedFile
						setMotion: MoveTo (selectedFile x?) (+ (selectedFile y?) 20) self
					)
				)
			)
			(4
				(selectedFile stopUpd:)
				(User canInput: 1)
				(if readingFile (self changeState: 1))
			)
			(5
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 3 IRISOUT)
				(curRoom setScript: rm3Script)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					(
						(or
							(Said '[turn,go,look]/2[<page]')
							(Said '[turn,go,look]/page<next,second')
							(Said 'read,look,see,turn/page[<next,second]')
						)
						(Print 3 60 #at -1 150)
					)
					((Said 'look[<in,around][/(drawer,cabinet)]')
						(if readingFile
							(Print 3 61)
						else
							(Print 3 62)
						)
					)
					(
						(or
							(Said 'read,see,look,get,open,pull/(colby,dickey,steve,simms,snider,george,jose)>')
							(Said '/(colby,dickey,steve,simms,snider,george,jose)>')
						)
						(cond 
							(readingFile
								(event claimed: 1)
								(Print 3 63 #at -1 150 #time 7)
							)
							(
								(or
									(Said '/colby')
									(Said '/<colby')
								)
								(= selectedFile colbyFile)
								(= theFile 0)
								(self changeState: 1)
							)
							(
								(or
									(Said '/dickey')
									(Said '/<dickey')
								)
								(= selectedFile dickeyFile)
								(= theFile 1)
								(self changeState: 1)
							)
							(
								(or
									(Said '/steve')
									(Said '/<steve')
								)
								(= selectedFile jonesFile)
								(= theFile 2)
								(self changeState: 1)
							)
							(
								(or
									(Said '/simms')
									(Said '/<simms')
								)
								(= selectedFile simmsFile)
								(= theFile 3)
								(self changeState: 1)
							)
							(
								(or
									(Said '/snider')
									(Said '/<snider')
								)
								(= selectedFile sniderFile)
								(= theFile 4)
								(self changeState: 1)
							)
							(
								(or
									(Said '/george')
									(Said '/<george')
								)
								(= selectedFile snowFile)
								(= theFile 5)
								(self changeState: 1)
							)
							(
								(or
									(Said '/jose')
									(Said '/<jose')
								)
								(= selectedFile valanciaFile)
								(= theFile 6)
								(self changeState: 1)
							)
							(else
								(event claimed: 1)
								(Print 3 64 #at -1 150)
							)
						)
					)
					((Said 'exit,close,exit,close/(drawer,cabinet)[<file]')
						(if readingFile
							(Print 3 63 #at -1 150 #time 7)
						else
							(self changeState: 5)
						)
					)
					(
						(or
							(Said 'close,replace[/file]')
							(Said 'deposit[<back,away]/file')
						)
						(if readingFile
							(Print 3 65)
							(= readingFile 0)
							(self changeState: 3)
						else
							(Print 3 66 #at -1 150)
						)
					)
				)
			)
		)
	)
)
