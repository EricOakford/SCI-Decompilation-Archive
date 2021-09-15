;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm220 0
)

(local
	local0
	[plotString 222]
)
(procedure (PrintPlot &tmp t)
	(Print @plotString
		#at 10 5
		#width 290
		#time (= t (PrintDelay @plotString))
		#dispose
	)
	(return (+ 3 t))
)

(instance rm220 of Room
	(properties
		picture 220
		horizon 67
		north 310
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(if (and (Btst fBackInLeisureSuit) (not (Btst fCredits220)))
			(Load VIEW 220)
			(Load VIEW 221)
			(Load FONT 9)
			(Load SOUND 110)
			(aCredit1 init:)
			(aCredit2 init:)
		)
		(cond 
			((== prevRoomNum 300)
				(ego posn: 6 175)
			)
			((== prevRoomNum 210)
				(ego posn: 2 151)
			)
			((== prevRoomNum 310)
				(ego posn: 316 70)
			)
			((== prevRoomNum 230)
				(ego posn: 316 142)
			)
			(else
				(ego posn: 316 182)
			)
		)
		(NormalEgo)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(cond 
				((& (ego onControl:) cBLUE)
					(curRoom newRoom: 300)
				)
				((& (ego onControl:) cGREEN)
					(curRoom newRoom: 210)
				)
				((& (ego onControl:) cCYAN)
					(curRoom newRoom: 310)
				)
				((& (ego onControl:) cRED)
					(curRoom newRoom: 230)
				)
				((& (ego onControl:) cMAGENTA)
					(curRoom newRoom: 250)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 5)
			)
			(1
				(cond 
					((not (Btst fBrokeUp))
						(HandsOff)
						(Print 220 2)
						(ego loop: 1)
						(Animate (cast elements?) FALSE)
						(curRoom newRoom: 210)
					)
					((not (Btst fBackInLeisureSuit))
						(HandsOff)
						(= cycles 25)
					)
				)
			)
			(2
				(Format @plotString 220 3)
				(ego setMotion: MoveTo 8 153 self)
				(= seconds (PrintPlot))
			)
			(3)
			(4
				(Format @plotString 220 4)
				(= seconds (PrintPlot))
			)
			(5
				(Format @plotString 220 5)
				(= seconds (PrintPlot))
			)
			(6
				(Format @plotString 220 6)
				(= seconds (PrintPlot))
			)
			(7
				(Format @plotString 220 7)
				(= seconds (PrintPlot))
			)
			(8
				(Format @plotString 220 8)
				(= seconds (PrintPlot))
			)
			(9
				(Format @plotString 220 9)
				(ego setMotion: MoveTo 85 153 self)
				(= seconds (PrintPlot))
			)
			(10)
			(11
				(Format @plotString 220 10)
				(music fade:)
				(= seconds (PrintPlot))
			)
			(12
				(Format @plotString 220 11)
				(= seconds (PrintPlot))
			)
			(13
				(aBooth init: setMotion: MoveTo 111 153 self)
				(if (> machineSpeed 39)
					(music number: 110 loop: 1 play:)
				)
			)
			(14
				(ego loop: 2)
				(= cycles 22)
			)
			(15
				(ego loop: 3)
				(= cycles 22)
			)
			(16
				(ego setPri: 10 setMotion: MoveTo 107 153 self)
			)
			(17
				(if (<= machineSpeed 39) (music number: 110 loop: 1 play:))
				(ego view: 221 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(18
				(ego
					loop: 2
					cel: 0
					posn: (ego x?) (- (ego y?) 16)
					setCycle: EndLoop self
				)
			)
			(19
				(= cycles 22)
			)
			(20
				(ego view: 221 loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(21
				(Print 220 12 #at -1 10 #font 9 #time 3 #dispose)
				(ShakeScreen 7 shakeSDiagonal)
				(= seconds 5)
			)
			(22
				(= currentEgoView 700)
				(NormalEgo loopW)
				(HandsOff)
				(ego
					posn: (ego x?) (+ (ego y?) 16)
					setPri: 10
					setMotion: MoveTo 85 153 self
				)
			)
			(23
				(= cycles 22)
			)
			(24
				(aBooth setMotion: MoveTo 111 211 self)
				(= cycles 33)
			)
			(25
				(Bset fBackInLeisureSuit)
				(ego setMotion: MoveTo 107 153 self)
				(Format @plotString 220 13)
				(= seconds (PrintPlot))
			)
			(26
				(= cycles 33)
			)
			(27
				(aBooth stopUpd:)
			)
			(28
				(NormalEgo loopE)
				(PutInRoom iWood 210)
				(PutInRoom iCreditCard 216)
				(= currentStatus egoNORMAL)
				(music number: 299 loop: musicLoop play:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(not (event claimed?))
				modelessDialog
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event claimed: TRUE)
			(cls)
			(self cue:)
		)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/building,office,club')
				(Print 220 0)
			)
			(if (Said '[/area]')
				(Print 220 1)
			)
		)
	)
)

(instance aCredit1 of Prop
	(properties
		y 131
		x 288
		view 220
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors:)
	)
)

(instance aCredit2 of Prop
	(properties
		y 154
		x 288
		view 220
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 ignoreActors: setScript: CreditsScript)
	)
)

(instance CreditsScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
			)
			(1
				(aCredit1 setCycle: EndLoop)
				(= cycles 16)
			)
			(2
				(aCredit2 setCycle: EndLoop)
				(= cycles 22)
			)
			(3
				(aCredit2 setCycle: BegLoop self)
			)
			(4
				(aCredit2 loop: 2 setCycle: EndLoop)
				(= cycles 22)
			)
			(5
				(Bset fCredits220)
				(aCredit1 setCycle: BegLoop)
				(aCredit2 setCycle: BegLoop self)
			)
			(6
				(aCredit1 dispose:)
				(aCredit2 dispose:)
			)
		)
	)
)

(instance aBooth of Actor
	(properties
		y 211
		x 111
		view 221
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self ignoreActors: setLoop: 0 setPri: 11)
	)
)
