;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include game.sh)
(use Main)
(use atsgl)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	leargue 0
)

(local
	ethelTalkCount
	local1
	lillTalkCount
	[local3 2]
	argueCount
	local6
	local7
	local8
)
(procedure (EthelPrint)
	(if (< global172 100)
		(lHead stopUpd:)
		(Lillian stopUpd:)
		(Ethel loop: 3 cycleSpeed: 1 setCycle: Forward)
		(Print &rest
			#at 175 125
			#font 4
			#width 125
			#mode teJustCenter
			#dispose
		)
	)
)

(procedure (LillPrint)
	(if (< global172 100)
		(Ethel stopUpd:)
		(Lillian loop: 1 cycleSpeed: 1 setCycle: Forward)
		(lHead cycleSpeed: 1 setCycle: Forward)
		(Print &rest
			#at 10 45
			#font 4
			#width 125
			#mode teJustCenter
			#dispose
		)
	)
)

(instance leargue of Region
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(LoadMany 143 243 221 226)
		(LoadMany VIEW 501 512)
		(LoadMany 142 4 6)
		(= [global377 3] 221)
		(= [global377 5] 226)
		(Lillian init: hide:)
		(= gDoor Lillian)
		(if (or (== [global368 4] 1) (== global203 2))
			(= global203 2)
			(if (or (!= prevRoomNum 45) (== global200 1))
				(= global200 1)
				(Ethel
					view: 325
					illegalBits: 0
					loop: 1
					cel: 6
					posn: 133 149
					ignoreActors:
					setPri: 13
					init:
				)
			)
			(Lillian
				view: 501
				loop: 4
				cel: 0
				illegalBits: 0
				posn: 120 148
				cycleSpeed: 1
				ignoreActors:
				show:
			)
			(lHead ignoreActors: TRUE setPri: 11 init:)
			(self setScript: talkActions)
		)
	)
	
	(method (doit)
		(if (< local8 70) (++ local8))
		(if (and local6 local7 (not script) (< global172 100))
			(self setScript: casTalk)
		)
		(if (== global172 99)
			(Lillian setCycle: 0)
			(lHead setCycle: 0)
			(cls)
			(EthelPrint 230 0)
			(++ global172)
		)
		(if (== global172 110)
			(cls)
			(Ethel setCycle: 0)
		)
		(if
			(and
				(== [global368 4] 1)
				(== global203 1)
				(not script)
				(or (== (ego view?) 0) (== (ego view?) 11))
			)
			(HandsOff)
			(self setScript: comeBack)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(cond 
				((Said 'examine>')
					(if (Said '/people,person,girl')
						(cond 
							((and (== global203 2) (== global200 1))
								(Print 230 1)
							)
							((== global203 2)
								(ParseName {lillian})
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
				)
				((Said 'converse/person,men')
					(Print 230 2)
				)
				((Said 'converse/people')
					(Print 230 3)
				)
			)
		)
	)
)

(instance talkActions of Script

	(method (doit)
		(super doit:)
		(if (> global172 98)
			(client setScript: 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== global200 0)
					(= seconds 5)
				else
					(= cycles 1)
				)
			)
			(1
				(if (== global200 0)
					(++ global200)
					(= local1 1)
					(if (ego inRect: 145 157 160 162)
						(ego setMotion: MoveTo (- (ego x?) 50) (ego y?))
					)
					(Ethel
						view: 325
						setLoop: 0
						illegalBits: cWHITE
						posn: 295 121
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: MoveTo 157 161 self
						init:
					)
				else
					(= local1 0)
					(= cycles 1)
				)
			)
			(2
				(if local1
					(Ethel
						loop: 1
						cel: 6
						ignoreActors:
						posn: 133 149
						setPri: 13
						setAvoider: 0
						setCycle: BegLoop self
					)
					(DisposeScript AVOIDER)
				else
					(= cycles 1)
				)
				(|= global208 $0028)
				(HandsOn)
			)
			(3
				(User canInput: FALSE)
				(if local1
					(EthelPrint 230 4)
				else
					(if (== (= argueCount (& global178 $7fff)) global178)
						(if (< global178 10)
							(++ global178)
						else
							(= global178 -32766)
						)
					else
						(switch argueCount
							(2 (= global178 -32764))
							(4 (= global178 -32762))
							(6 (= global178 -32761))
							(7 (= global178 -32759))
							(9 (= global178 -32758))
							(10 (= global178 -32766))
						)
					)
					(switch argueCount
						(0 (EthelPrint 230 5))
						(1 (LillPrint 230 6))
						(2 (EthelPrint 230 7))
						(3 (LillPrint 230 8))
						(4 (EthelPrint 230 9))
						(5 (LillPrint 230 10))
						(6 (LillPrint 230 11))
						(7 (EthelPrint 230 12))
						(8 (LillPrint 230 13))
						(9 (LillPrint 230 14))
						(else  (EthelPrint 230 15))
					)
				)
				(= seconds 7)
			)
			(4
				(cls)
				(Lillian setCycle: 0 cel: 0 loop: 4)
				(lHead cel: 0 forceUpd:)
				(Ethel cel: 0 forceUpd:)
				(= seconds 1)
			)
			(5
				(cls)
				(if (== local1 0)
					(= seconds 5)
					(switch argueCount
						(0 (EthelPrint 230 16))
						(1 (LillPrint 230 17))
						(5 (LillPrint 230 18))
						(7 (EthelPrint 230 19))
						(8 (LillPrint 230 20))
						(else  (= cycles 1))
					)
				else
					(= cycles 1)
				)
				(= local1 0)
			)
			(6
				(= local8 0)
				(Lillian setScript: lilActions)
				(Ethel setScript: ethelActions)
				(client setScript: 0)
				(User canInput: 1)
				(cls)
			)
		)
	)
)

(instance casTalk of Script
	
	(method (doit)
		(super doit:)
		(if (> global172 98)
			(client setScript: 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: FALSE)
				(= seconds 5)
				(switch gCurRoomNum_4
					(0 (EthelPrint 230 21))
					(1 (LillPrint 230 22))
					(2 (EthelPrint 230 23))
					(3 (LillPrint 230 24))
					(4 (EthelPrint 230 25))
					(5 (EthelPrint 230 26))
					(6 (LillPrint 230 27))
					(7 (EthelPrint 230 28))
					(8 (EthelPrint 230 29))
					(9 (EthelPrint 230 30))
					(else  (= seconds 1))
				)
			)
			(1
				(cls)
				(Lillian loop: 4 stopUpd:)
				(lHead cel: 0 forceUpd: stopUpd:)
				(Ethel cel: 0 forceUpd: stopUpd:)
				(= seconds 1)
			)
			(2
				(cls)
				(= seconds 5)
				(switch gCurRoomNum_4
					(0 (LillPrint 230 31))
					(1 (EthelPrint 230 32))
					(2 (LillPrint 230 33))
					(3 (EthelPrint 230 34))
					(4 (LillPrint 230 35))
					(5 (LillPrint 230 36))
					(6 (EthelPrint 230 37))
					(7 (LillPrint 230 38))
					(8 (LillPrint 230 39))
					(9 (LillPrint 230 40))
					(else  (= seconds 1))
				)
			)
			(3
				(cls)
				(lHead cel: 0 forceUpd: stopUpd:)
				(= seconds 5)
				(switch gCurRoomNum_4
					(8 (EthelPrint 230 41))
					(else  (= cycles 1))
				)
			)
			(4
				(= local8 0)
				(if (< gCurRoomNum_4 9)
					(++ gCurRoomNum_4)
				)
				(cls)
				(User canInput: TRUE)
				(Lillian setScript: lilActions)
				(Ethel setScript: ethelActions)
				(= local7 0)
				(= local6 0)
				(client setScript: 0)
			)
		)
	)
)

(instance ethelActions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Ethel cel: 0 loop: 1 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(1
				(Ethel cel: 0 loop: 2 setCycle: EndLoop)
				(= seconds 3)
			)
			(2
				(Ethel loop: 2 setCycle: BegLoop)
				(if (< (Random 1 100) 40)
					(= state 0)
				)
				(= seconds (Random 6 12))
			)
			(3
				(Ethel loop: 1 cel: 6 setCycle: BegLoop)
				(if (== local8 70)
					(client setScript: 0)
					(= local6 1)
				else
					(= state -1)
					(= seconds (Random 6 12))
				)
			)
		)
	)
)

(instance lilActions of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Lillian loop: 4 cel: 0 cycleSpeed: 2 setCycle: EndLoop)
				(lHead show: stopUpd:)
				(= seconds (Random 6 12))
			)
			(1
				(lHead show: stopUpd:)
				(Lillian loop: 6 setCycle: Forward)
				(if (< (Random 1 100) 50)
					(= state 2)
				)
				(= seconds (Random 5 8))
			)
			(2
				(Lillian
					loop: 4
					cel: (- (NumCels Lillian) 1)
					setCycle: BegLoop
				)
				(if (== local8 70)
					(= local7 1)
					(client setScript: 0)
				)
				(= state -1)
				(= seconds (Random 5 8))
			)
			(3
				(lHead hide:)
				(Lillian loop: 2 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(4
				(Lillian loop: 3 cycleSpeed: 1 setCycle: Forward)
				(= seconds 2)
			)
			(5
				(Lillian cel: 2 loop: 2 cycleSpeed: 1 setCycle: BegLoop)
				(= state 0)
				(= seconds (Random 5 8))
			)
		)
	)
)

(instance comeBack of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global203 2)
				(Lillian
					view: 500
					posn: -29 148
					setCycle: Walk
					setPri: 10
					setAvoider: ((Avoider new:) offScreenOK: TRUE)
					setMotion: MoveTo 55 148 self
					show:
				)
			)
			(1
				(Lillian setPri: -1 setMotion: MoveTo 97 151 self)
			)
			(2
				(Lillian setMotion: MoveTo 121 153 self)
			)
			(3
				(|= global208 $0020)
				(Lillian
					view: 501
					loop: 0
					cel: 4
					illegalBits: 0
					posn: 120 148
					cycleSpeed: 1
					setAvoider: 0
					ignoreActors:
					stopUpd:
				)
				(DisposeScript AVOIDER)
				(lHead setPri: 11 init:)
				(client setScript: talkActions)
			)
		)
	)
)

(instance Ethel of Actor
	
	(method (handleEvent event)
		(if
		(< (ego distanceTo: Ethel) (ego distanceTo: Lillian))
			(= global214 8)
		else
			(= global214 32)
		)
		(= theTalker talkETHEL)
		(cond 
			((and (MousedOn self event shiftDown) (not (& global207 $0008)))
				(event claimed: TRUE)
				(ParseName {ethel})
			)
			(
				(and
					(& global207 $0008)
					(or (MousedOn self event shiftDown) (Said 'examine/ethel'))
				)
				(event claimed: TRUE)
				(Print 230 1)
			)
			((Said 'converse/ethel')
				(switch ethelTalkCount
					(0 (Say 1 230 42))
					(1 (Say 1 230 43))
					(2 (Say 1 230 44))
					(3 (Say 1 230 45))
					(else  (Print 230 46))
				)
				(++ ethelTalkCount)
			)
			((Said 'ask[/ethel]/lil<about')
				(= global212 1)
				(= global209 event)
				(proc243_1 14 230 47)
			)
			((Said 'hear/ethel,lil')
				(Print 230 48)
			)
			((Said 'examine/drink,glass')
				(Print 230 49)
			)
			((Said 'examine/deliver')
				(Print 230 50)
			)
			((Said 'examine/handkerchief')
				(Print 230 51)
			)
			((Said 'get>')
				(cond 
					((Said '/drink,glass')
						(Print 230 52)
					)
					((Said '/handkerchief')
						(Print 230 53)
					)
				)
			)
		)
	)
)

(instance Lillian of Actor
	(properties
		y 134
		x 69
		view 500
	)
	
	(method (handleEvent event)
		(if (== global203 2)
			(= theTalker 6)
			(cond 
				((and (MousedOn self event shiftDown) (not (& global207 $0020)))
					(event claimed: TRUE) (ParseName {lillian})
				)
				(
					(and
						(& global207 $0020)
						(or (MousedOn self event shiftDown) (Said 'examine/lil'))
					)
					(event claimed: TRUE)
					(Print 230 1)
				)
				((Said 'converse/lil')
					(switch lillTalkCount
						(0 (Say 1 230 54))
						(1 (Say 1 230 55))
						(2 (Say 1 230 56))
						(3 (Say 1 230 57))
						(else  (Say 1 230 58))
					)
					(++ lillTalkCount)
				)
				((Said 'ask[/lil]/ethel<about')
					(= global212 1)
					(= global209 event)
					(proc243_1 10 230 59)
				)
			)
		)
	)
)

(instance lHead of Prop
	(properties
		y 115
		x 116
		view 501
		loop 9
		signal ignrAct
	)
)
