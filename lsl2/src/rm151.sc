;;; Sierra Script 1.0 - (do not remove this comment)
(script# 151)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm151 0
)

(local
	aBarber
	aChair
	talkedToBarber
)
(instance rm151 of Room
	(properties
		picture 125
		horizon 5
		south 51
	)
	
	(method (init)
		(Load VIEW 507)
		(Load VIEW 232)
		(super init:)
		((= aChair (Prop new:))
			view: 232
			loop: 1
			cel: 0
			posn: 167 116
			setPri: 8
			ignoreActors:
			init:
		)
		((= aBarber (Actor new:))
			view: 507
			loop: 2
			posn: 144 152
			setCycle: Walk
			illegalBits: -32768
			init:
		)
		(aBigFace
			view: 507
			setLoop: 9
			posn: 36 1117
			setPri: 15
			init:
		)
		(NormalEgo 3)
		(ego posn: 160 159 init:)
		(self setRegions: BARBER AIRPORT setScript: rm151Script)
	)
)

(instance rm151Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0002) (curRoom newRoom: 51))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if ((inventory at: iHairRejuvenator) ownedBy: curRoomNum)
					(= seconds 10)
				)
			)
			(1
				(if (not talkedToBarber)
					(Print 151 4)
					(= seconds (Random 12 25))
					(= state 0)
				)
			)
			(2
				(= seconds (= cycles 0))
				(= currentStatus egoSITTING)
				(HandsOff)
				(Print 151 5)
				(Print 151 6)
				(Print 151 7)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 157 120 self
				)
				(aBarber setMotion: MoveTo 185 119 self)
			)
			(3
				(ego hide:)
				(aChair view: 507 setLoop: 8 setCel: 255 setCycle: BegLoop)
			)
			(4
				(aBarber setMotion: MoveTo 179 113 self)
			)
			(5
				(aBarber
					setMotion: MoveTo 169 113 self
					illegalBits: 0
					ignoreActors:
				)
			)
			(6
				(aChair view: 507 setLoop: 4 setCycle: 0)
				(aBarber
					view: 507
					setLoop: 5
					posn: 165 86
					setCel: 0
					setCycle: EndLoop self
					setPri: 9
				)
			)
			(7
				(aChair stopUpd:)
				(aBarber setLoop: 6 cycleSpeed: 1 cel: 0 setCycle: Forward)
				(= seconds 5)
			)
			(8
				(aBarber setLoop: 7 cel: 0 setCycle: EndLoop self)
			)
			(9
				(aChair view: 232 setLoop: 2 setCel: 255)
				(aBarber
					loop: 2
					setLoop: -1
					setPri: 7
					posn: 169 112
					setCycle: Walk
					ignoreActors: 0
				)
				(Print 151 8 #at -1 20 #draw)
				(= seconds 3)
			)
			(10
				(Print 151 9 #at -1 20)
				(Print 151 10 #at -1 20)
				(= seconds 3)
			)
			(11
				(aChair setCycle: BegLoop self)
			)
			(12
				(= currentEgoView 100)
				(ego view: 100 setLoop: -1 loop: 3 ignoreActors: 0 show:)
				(aChair view: 232 loop: 1 cel: 0)
				(= seconds 2)
			)
			(13
				(aChair stopUpd:)
				(aBarber stopUpd:)
				(Print 151 11 #draw)
				(Print 151 12)
				(Print (Format @str 151 13 tritePhrase))
				(NormalEgo)
				(theGame changeScore: 3)
				(ego get: 21 setMotion: MoveTo 157 222 self)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if
			(or
				(Said 'bath,fix,ok,cut')
				(Said '(get<in)/barstool')
				(Said 'get/haircut,(cut<hair)')
			)
			(= talkedToBarber TRUE)
			(cond 
				((not (ego inRect: 148 117 180 127)) (Print 151 0))
				((!= currentEgoView 149) (Print 151 1))
				((== currentStatus 1009) (YouAre))
				(else (= hairDyedBlonde FALSE) (self changeState: 2))
			)
		)
		(if (Said 'look/man,bimbo,children')
			(aBigFace posn: 36 117 stopUpd:)
			(if (not lookedAtRosella)
				(= lookedAtRosella TRUE)
				(theGame changeScore: 3)
			)
			(Timer setReal: aBigFace 5)
			(HandsOff)
		)
		(if (Said 'call/man,children,bimbo')
			(= talkedToBarber TRUE)
			(if (== currentEgoView 100)
				(Print 151 2)
			else
				(Print 151 3)
			)
		)
	)
)

(instance aBigFace of Prop
	(properties)
	
	(method (cue)
		(Print 151 14)
		(Print 151 15 #at 55 155 #width 210)
		(self posn: 36 1117)
		(Print 151 16 #at -1 20 #draw)
		(Print 151 17)
		(Print 151 18 #at -1 20)
		(HandsOn)
	)
)
