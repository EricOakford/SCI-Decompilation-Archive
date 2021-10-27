;;; Sierra Script 1.0 - (do not remove this comment)
(script# 91)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm91 0
)

(local
	captain
	captainHead
	guitarist
	drummer
	saxophonist
	printObj
	airplaneFlyingIntoSunset
	airplaneFinale
	sonnyAndMarie
	local9
	heart1
	heart2
	heart3
)
(instance band of Sound
	(properties
		number 7
		priority 15
	)
)

(instance Extro of Sound
	(properties
		number 15
		priority 15
		loop -1
	)
)

(instance WedBells of Sound
	(properties
		number 48
		priority 15
	)
)

(instance rm91 of Room
	(properties
		picture 94
		style $0007
	)
	
	(method (init)
		(HandsOff)
		(theGame setSpeed: 4)
		(Load rsPIC 91)
		(Load rsVIEW 207)
		(Load rsVIEW 282)
		(Load rsVIEW 276)
		(Load rsPIC 95)
		(Load rsVIEW 280)
		(Load rsVIEW 259)
		(Load rsVIEW 304)
		(Load rsVIEW 285)
		(Load rsPIC 89)
		(Load rsSOUND 7)
		(Load rsSOUND 15)
		(Load rsSOUND 48)
		(super init:)
		(curRoom setScript: recommendationsScript)
	)
)

(instance recommendationsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 91 0 #time 8)
				(Print 91 1 #time 16)
				(Print 91 2 #time 16)
				(= seconds 5)
			)
			(1
				(Print 91 3 #time 10)
				(Print 91 4 #time 6)
				(Print 91 5 #time 22)
				(Print 91 6 #time 14)
				(Print 91 7 #time 20)
				(Print 91 8 #time 6)
				(Print 91 9 #time 20)
				(Print 91 10 #time 20)
				(Print 91 11 #time 6)
				(Print 91 12 #time 30)
				(Print 91 13 #time 30)
				(Print 91 14 #time 20)
				(= cycles 10)
			)
			(2
				(Print 91 15 #time 10)
				(curRoom setScript: airportScript)
			)
		)
	)
)

(instance eatIceCream of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(captainHead hide:)
				(captain
					setLoop: 1
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(= state -1)
				(captainHead
					setLoop: 2
					setCel: 0
					show:
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= seconds (Random 4 8))
			)
		)
	)
)

(instance airportScript of Script
	(properties)
	
	(method (init)
		(curRoom drawPic: 91)
		((View new:)
			view: 276
			posn: 207 149
			loop: 0
			cel: 0
			setPri: 11
			init:
			addToPic:
		)
		((View new:)
			view: 276
			posn: 306 151
			loop: 0
			cel: 1
			setPri: 11
			init:
			addToPic:
		)
		((View new:)
			view: 276
			posn: 250 152
			loop: 1
			cel: 0
			setPri: 11
			init:
			addToPic:
		)
		((View new:)
			view: 282
			posn: 47 191
			loop: 0
			cel: 0
			setPri: 15
			init:
			addToPic:
		)
		((View new:)
			view: 282
			posn: 71 190
			loop: 0
			cel: 1
			setPri: 14
			init:
			addToPic:
		)
		((View new:)
			view: 282
			posn: 136 190
			loop: 0
			cel: 2
			setPri: 14
			init:
			addToPic:
		)
		((= captainHead (Prop new:))
			view: 282
			posn: 185 142
			loop: 2
			cel: 0
			setPri: 15
			init:
			hide:
		)
		((= captain (Prop new:))
			view: 282
			posn: 179 191
			loop: 1
			cel: 0
			setPri: 14
			init:
			setScript: eatIceCream
		)
		((= guitarist (Prop new:))
			view: 207
			posn: 241 160
			loop: 1
			setPri: 12
			init:
			cycleSpeed: 1
			setCycle: Forward
		)
		((= drummer (Prop new:))
			view: 207
			posn: 274 157
			loop: 0
			setPri: 12
			init:
			cycleSpeed: 1
			setCycle: Forward
		)
		((= saxophonist (Prop new:))
			view: 207
			posn: 293 159
			loop: 2
			setPri: 12
			init:
			cycleSpeed: 2
			setCycle: Forward
		)
		(RedrawCast)
		(band play:)
		(super init:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= printObj (Print 91 16 #at 25 20 #dispose))
				(= seconds 11)
			)
			(1
				(cls)
				(= printObj (Print 91 17 #at 25 20 #dispose))
				(= seconds 8)
			)
			(2
				(cls)
				(= printObj (Print 91 18 #at 25 20 #dispose))
				(= seconds 10)
			)
			(3
				(cls)
				(= printObj (Print 91 19 #at 25 20 #dispose))
				(= seconds 11)
			)
			(4
				(cls)
				(= printObj (Print 91 20 #at 25 20 #dispose))
				(= seconds 10)
			)
			(5
				(eatIceCream dispose:)
				(cls)
				(= printObj (Print 91 21 #at 25 20 #dispose))
				(= seconds 11)
			)
			(6
				(if (!= (band prevSignal?) -1)
					(-- state)
					(= cycles 2)
				else
					(cls)
					(cast eachElementDo: #dispose)
					(curRoom setScript: flyingIntoSunsetScript)
				)
			)
		)
	)
)

(instance flyingIntoSunsetScript of Script
	(properties)
	
	(method (init)
		((= airplaneFlyingIntoSunset (Actor new:))
			view: 280
			loop: 0
			cel: 0
			posn: 183 189
			setPri: -1
			init:
		)
		(band stop:)
		(curRoom drawPic: 95)
		(super init:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(airplaneFlyingIntoSunset
					cel: 0
					setMotion: MoveTo 190 177 self
				)
			)
			(1
				(Extro play:)
				(airplaneFlyingIntoSunset
					cel: 1
					setMotion: MoveTo 192 163 self
				)
			)
			(2
				(airplaneFlyingIntoSunset
					cel: 2
					setMotion: MoveTo 196 149 self
				)
			)
			(3
				(airplaneFlyingIntoSunset
					cel: 3
					setMotion: MoveTo 203 137 self
				)
			)
			(4
				(airplaneFlyingIntoSunset
					cel: 6
					setMotion: MoveTo 209 125 self
				)
			)
			(5
				(cls)
				(= printObj (Print 91 22 #at -1 20 #dispose))
				(airplaneFlyingIntoSunset
					cel: 6
					setMotion: MoveTo 214 114 self
				)
			)
			(6
				(airplaneFlyingIntoSunset
					cel: 6
					setMotion: MoveTo 219 104 self
				)
			)
			(7
				(airplaneFlyingIntoSunset
					cel: 7
					setMotion: MoveTo 226 94 self
				)
			)
			(8
				(airplaneFlyingIntoSunset
					cel: 8
					setMotion: MoveTo 235 86 self
				)
			)
			(9
				(airplaneFlyingIntoSunset
					cel: 9
					setMotion: MoveTo 247 79 self
				)
			)
			(10
				(airplaneFlyingIntoSunset
					cel: 10
					setMotion: MoveTo 261 73 self
				)
			)
			(11
				(airplaneFlyingIntoSunset
					cel: 11
					setMotion: MoveTo 274 68 self
				)
			)
			(12
				(cls)
				(airplaneFlyingIntoSunset
					cel: 12
					setMotion: MoveTo 288 63 self
				)
			)
			(13
				(airplaneFlyingIntoSunset
					cel: 13
					setMotion: MoveTo 304 58 self
				)
			)
			(14
				(airplaneFlyingIntoSunset
					cel: 14
					setMotion: MoveTo 311 52 self
				)
			)
			(15
				(airplaneFlyingIntoSunset
					cel: 15
					setMotion: MoveTo 312 45 self
				)
			)
			(16
				(airplaneFlyingIntoSunset
					setLoop: 1
					cel: 0
					setMotion: MoveTo 312 40 self
				)
			)
			(17
				(airplaneFlyingIntoSunset
					cel: 1
					setMotion: MoveTo 312 29 self
				)
			)
			(18
				(airplaneFlyingIntoSunset
					cel: 1
					setMotion: MoveTo 316 25 self
				)
			)
			(19
				(airplaneFlyingIntoSunset
					cel: 2
					setMotion: MoveTo 323 23 self
				)
			)
			(20
				(airplaneFlyingIntoSunset
					cel: 2
					setMotion: MoveTo 350 20 self
				)
			)
			(21
				(airplaneFlyingIntoSunset dispose:)
				(cast eachElementDo: #dispose)
				(curRoom setScript: finaleScript)
			)
		)
	)
)

(instance finaleScript of Script
	(properties)
	
	(method (init)
		((= airplaneFinale (Actor new:))
			view: 259
			setLoop: 7
			setCel: 0
			posn: 313 118
			setPri: 5
			setStep: 1 1
			moveSpeed: 0
			init:
			setMotion: MoveTo -60 118
		)
		((View new:)
			view: 259
			setLoop: 4
			setCel: 0
			posn: 146 162
			setPri: 12
			init:
			addToPic:
		)
		((View new:)
			view: 259
			setLoop: 5
			setCel: 0
			posn: 239 177
			setPri: 13
			init:
			addToPic:
		)
		((View new:)
			view: 259
			setLoop: 6
			setCel: 0
			posn: 220 140
			setPri: 10
			init:
			addToPic:
		)
		((View new:)
			view: 259
			setLoop: 4
			setCel: 0
			posn: 63 54
			setPri: 2
			init:
			addToPic:
		)
		((View new:)
			view: 259
			setLoop: 4
			setCel: 0
			posn: 37 150
			setPri: 7
			init:
			addToPic:
		)
		((View new:)
			view: 259
			setLoop: 6
			setCel: 0
			posn: 6 57
			setPri: 2
			init:
			addToPic:
		)
		(curRoom drawPic: 89)
		(super init:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(= printObj (Print 91 23 #draw #at 10 20 #dispose))
				(= seconds 2)
			)
			(2
				((View new:)
					view: 304
					setLoop: 0
					setCel: 0
					posn: 245 87
					setPri: 10
					init:
					stopUpd:
				)
				((= sonnyAndMarie (Prop new:))
					view: 304
					setLoop: 1
					setCel: 1
					posn: 245 82
					setPri: 11
					init:
					stopUpd:
				)
				(= seconds 2)
			)
			(3
				(cls)
				(= printObj (Print 91 24 #draw #at 40 20 #dispose))
				(= seconds 3)
			)
			(4
				(cls)
				(= printObj (Print 91 25 #at 10 20 #dispose))
				(= seconds 5)
			)
			(5
				(sonnyAndMarie setCel: 2)
				(cls)
				(Extro stop:)
				(WedBells play:)
				(= printObj (Print 91 26 #draw #at 10 20 #dispose))
				(= seconds 4)
			)
			(6
				(cls)
				(= printObj (Print 91 27 #at 100 40 #dispose))
				(sonnyAndMarie setCycle: EndLoop)
				(= seconds 2)
			)
			(7
				(cls)
				(sonnyAndMarie setCycle: BegLoop self)
			)
			(8
				((= heart1 (Actor new:))
					view: 285
					setLoop: 0
					setCel: 0
					posn: (- (airplaneFinale x?) 20) 100
					setPri: 6
					init:
					setStep: 1 1
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo -60 100
				)
				((= heart2 (Actor new:))
					view: 285
					setLoop: 1
					setCel: 1
					posn: (- (airplaneFinale x?) 10) 93
					setPri: 6
					init:
					setStep: 1 1
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo -50 93
				)
				((= heart3 (Actor new:))
					view: 285
					setLoop: 2
					setCel: 2
					posn: (- (airplaneFinale x?) 5) 104
					setPri: 9
					init:
					setStep: 1 1
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo -40 100 self
				)
			)
			(9
				(sonnyAndMarie
					setLoop: 2
					setCel: 0
					posn: 234 60
					setPri: 12
					setCycle: EndLoop self
				)
			)
			(10
				(sonnyAndMarie stopUpd:)
				(= printObj (Print 91 28 #at -1 100 #dispose))
				(= seconds 10)
			)
			(11
				(cls)
				(= printObj (Print 91 29 #at -1 100 #dispose))
			)
		)
	)
)
