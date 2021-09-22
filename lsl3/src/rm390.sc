;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm390 0
)
(synonyms
	(babe bambi dale)
)

(local
	hitRearWall
	[str 200]
)
(procedure (BambiSays)
	(cls)
	(Print @str
		#at 10 10
		#title {Bambi says...}
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(procedure (YouSay)
	(cls)
	(Print @str
		#at 160 10
		#title {You say...}
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(instance rm390 of Room
	(properties
		picture 390
	)
	
	(method (init)
		(aMike init:)
		(super init:)
		(if (and (not (Btst fScrewedBambi)) (not (Btst fBambiGone)))
			(aMonitorRight init:)
			(aMonitorLeft init:)
			(aBambi init:)
		)
		(self setScript: RoomScript)
		(if (== prevRoomNum 395)
			(ego posn: 135 155 loop: 2)
			(if (!= currentStatus egoAUTO)
				(= currentStatus egoNORMAL)
			else
				(music stop:)
				(Load VIEW 392)
				(Load VIEW 393)
				(Load VIEW 397)
				(Load SOUND 390)
				(aBambi setScript: 0)
				(RoomScript changeState: 1)
			)
		else
			(ego posn: 243 134 setLoop: 1)
		)
		(if (!= currentStatus egoAUTO)
			(NormalEgo)
			(if (cast contains: aBambi)
				(BambiScript changeState: 1)
			)
		)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (< (ego priority?) 5)
			(if (not hitRearWall)
				(= hitRearWall TRUE)
				(ShakeScreen 1 1)
				(Print 390 0)
				(ego setMotion: 0)
			)
		else
			(= hitRearWall FALSE)
		)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 360)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(music stop:)
				(= cycles 2)
				(= seconds 3)
			)
			(2
				(music stop:)
			)
			(3
				(music stop:)
				(= saveSpeed (theGame setSpeed: 6))
				(Print 390 10)
				(music number: 390 loop: -1 play: self)
				(aSpeakerLeft init:)
				(aSpeakerRight init:)
				(aBambi cycleSpeed: 1 setCycle: Forward)
				(= cycles 30)
			)
			(4
				(aBambi setLoop: 0)
				(= cycles 30)
			)
			(5
				(aBambi setLoop: 1)
				(= cycles 30)
			)
			(6
				(aBambi setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(7
				(aBambi setLoop: 3 setCycle: Forward)
				(= cycles 30)
			)
			(8
				(aBambi setLoop: 2 setCel: 255 setCycle: BegLoop self)
			)
			(9
				(aBambi setLoop: 4 setCycle: Forward)
				(= cycles 30)
			)
			(10
				(Format @str 390 11)
				(YouSay)
				(aBambi view: 392 setLoop: 0 cycleSpeed: 0)
				(= cycles 30)
			)
			(11
				(Format @str 390 12)
				(BambiSays)
				(aBambi setLoop: 1)
				(= cycles 30)
			)
			(12
				(Format @str 390 13)
				(YouSay)
				(= cycles 30)
			)
			(13
				(aMike setCycle: EndLoop)
				(= cycles 30)
			)
			(14
				(aMike stopUpd:)
				(aBambi setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(15
				(aBambi setLoop: 3 setCycle: Forward)
				(= cycles 30)
			)
			(16
				(Format @str 390 14)
				(YouSay)
				(aBambi setLoop: 4)
				(= cycles 30)
			)
			(17
				(aBambi setLoop: 5 setCel: 0 setCycle: EndLoop self)
			)
			(18
				(Format @str 390 15)
				(BambiSays)
				(aBambi setLoop: 6 setCycle: Forward)
				(= cycles 30)
			)
			(19
				(aMonitorLeft dispose:)
				(aMonitorRight dispose:)
				(aLens init: setCycle: EndLoop self)
			)
			(20
				(aLens stopUpd:)
				(= seconds 5)
			)
			(21
				(= seconds 0)
				(Format @str 390 16)
				(BambiSays)
				(music fade:)
				(aBambi
					view: 393
					setPri: -1
					setLoop: 0
					posn: 157 155
					setCycle: Walk
					setMotion: MoveTo 180 154 self
				)
			)
			(22
				(aBambi setMotion: MoveTo 200 134 self)
			)
			(23
				(ego
					setCycle: Walk
					setLoop: -1
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 180 154 self
				)
				(aBambi setScript: BambiScript)
				(BambiScript changeState: 6)
				(theGame setSpeed: saveSpeed)
			)
			(24
				(ego setMotion: MoveTo 200 134 self)
			)
			(25
				(ego setMotion: MoveTo 333 134)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'stand,get,jump,climb<on/backstage')
				(Print 390 1)
			)
			((Said 'get')
				(Print 390 2)
			)
			((Said 'drag<on/camera')
				(Print 390 3)
			)
			((Said 'make/tape')
				(Print 390 4)
			)
			(
				(or
					(Said '/class,(work<out),aerobic')
					(Said '//class,(work<out),aerobic')
					(Said 'class,dance,naked,naked,(work<out),class')
				)
				(if larryBuffed
					(Print 390 5)
				else
					(Print 390 6)
				)
			)
			((Said 'look>')
				(cond 
					((Said '/camera,equipment,camera,tape,microphone')
						(Print 390 7)
					)
					((Said '/burn')
						(Print 390 8)
					)
					((Said '[/area]')
						(Printf 390 9
							(cond 
								((cast contains: aBambi)
									{A beautiful blond woman stands on the stage with a puzzled expression on her face.}
								)
								((Btst fBambiGone)
									{It looks like someone was recently here making a video.}
								)
								(else
									{The equipment seems to have suffered some sort of meltdown!}
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance aBambi of Actor
	(properties
		y 155
		x 160
		view 391
	)
	
	(method (init)
		(super init:)
		(self
			illegalBits: 0
			ignoreHorizon:
			ignoreActors:
			setPri: 15
			setScript: BambiScript
		)
	)
)

(instance BambiScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 2 2)
		(switch (= state newState)
			(0)
			(1
				(aBambi
					setCycle: Forward
					setLoop:
					(switch (Random 0 2)
						(0 0)
						(1 1)
						(else  4)
					)
				)
				(= seconds (Random 5 11))
			)
			(2
				(aBambi setLoop: 1 setCel: 0)
				(= seconds (Random 2 4))
				(= state 0)
			)
			(3)
			(4
				(aBambi setLoop: 1 setCel: 0)
				(if (Btst fMetBambi)
					(Print 390 24)
				else
					(Printf 390 25 introductoryPhrase)
				)
				(= seconds 3)
			)
			(5
				(if (Btst fMetBambi)
					(Print 390 26)
				else
					(Bset fMetBambi)
					(if larryBuffed
						(Print 390 27)
					else
						(Print 390 28)
					)
				)
				(= currentStatus 14)
				(curRoom newRoom: 395)
			)
			(6
				(aBambi setMotion: MoveTo 333 134)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/babe')
				(cond 
					((not (& (ego onControl:) cCYAN))
						(Print 390 17)
					)
					((Btst fNotShower)
						(Print 390 18)
					)
					((Btst fNotUseSoap)
						(Print 390 19)
					)
					((Btst fNotUseDeodorant)
						(Print 390 20)
					)
					(else
						(self changeState: 4)
					)
				)
			)
			((Said '/boob,body')
				(Print 390 21)
				(Print 390 22
					#at -1 144
				)
			)
			((Said '/babe')
				(if (not (& (ego onControl:) cCYAN))
					(NotClose)
				else
					(Print 390 23)
				)
			)
		)
	)
)

(instance aSpeakerLeft of Prop
	(properties
		y 154
		x 36
		view 390
		loop 2
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Forward)
	)
)

(instance aSpeakerRight of Prop
	(properties
		y 153
		x 294
		view 390
		loop 3
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Forward)
	)
)

(instance aLens of Prop
	(properties
		y 73
		x 60
		view 390
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 9 ignoreActors:)
	)
)

(instance aMike of Prop
	(properties
		y 61
		x 168
		view 390
		loop 1
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 2 stopUpd:)
	)
)

(instance aMonitorLeft of Prop
	(properties
		y 121
		x 93
		view 396
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 ignoreActors:)
	)
	
	(method (doit)
		(self
			view: (+ 5 (aBambi view?))
			loop: (aBambi loop?)
			cel: (aBambi cel?)
		)
		(super doit:)
	)
)

(instance aMonitorRight of Prop
	(properties
		y 121
		x 229
		view 396
	)
	
	(method (init)
		(super init:)
		(self setPri: 11 setCycle: Forward ignoreActors:)
	)
	
	(method (doit)
		(self
			view: (+ 5 (aBambi view?))
			loop: (aBambi loop?)
			cel: (aBambi cel?)
		)
		(super doit:)
	)
)
