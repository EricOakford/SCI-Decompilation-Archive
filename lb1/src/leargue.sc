;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include sci.sh)
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
	local0
	local1
	local2
	[local3 2]
	local5
	local6
	local7
	local8
)
(procedure (localproc_000c)
	(if (< global172 100)
		(lHead stopUpd:)
		(Lillian stopUpd:)
		(Ethel loop: 3 cycleSpeed: 1 setCycle: Fwd)
		(Print
			&rest
			#at
			175
			125
			#font
			4
			#width
			125
			#mode
			1
			#dispose
		)
	)
)

(procedure (localproc_0058)
	(if (< global172 100)
		(Ethel stopUpd:)
		(Lillian loop: 1 cycleSpeed: 1 setCycle: Fwd)
		(lHead cycleSpeed: 1 setCycle: Fwd)
		(Print
			&rest
			#at
			10
			45
			#font
			4
			#width
			125
			#mode
			1
			#dispose
		)
	)
)

(instance leargue of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
		(LoadMany 143 243 221 226)
		(LoadMany 128 501 512)
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
			(lHead ignoreActors: 1 setPri: 11 init:)
			(self setScript: talkActions)
		)
	)
	
	(method (doit)
		(if (< local8 70) (++ local8))
		(if
		(and local6 local7 (not script) (< global172 100))
			(self setScript: casTalk)
		)
		(if (== global172 99)
			(Lillian setCycle: 0)
			(lHead setCycle: 0)
			(cls)
			(localproc_000c 230 0)
			(++ global172)
		)
		(if (== global172 110) (cls) (Ethel setCycle: 0))
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
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) evSAID)
			(cond 
				((Said 'examine>')
					(if (Said '/people,person,girl')
						(cond 
							((and (== global203 2) (== global200 1)) (Print 230 1))
							((== global203 2) (ParseName {lillian}))
							(else (event claimed: 0))
						)
					)
				)
				((Said 'converse/person,men') (Print 230 2))
				((Said 'converse/people') (Print 230 3))
			)
		)
	)
)

(instance talkActions of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (> global172 98) (client setScript: 0))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== global200 0) (= seconds 5) else (= cycles 1))
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
						illegalBits: -32768
						posn: 295 121
						setCycle: Walk
						setAvoider: (Avoid new:)
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
						setCycle: Beg self
					)
					(DisposeScript 985)
				else
					(= cycles 1)
				)
				(= global208 (| global208 $0028))
				(HandsOn)
			)
			(3
				(User canInput: 0)
				(if local1
					(localproc_000c 230 4)
				else
					(if (== (= local5 (& global178 $7fff)) global178)
						(if (< global178 10)
							(++ global178)
						else
							(= global178 -32766)
						)
					else
						(switch local5
							(2 (= global178 -32764))
							(4 (= global178 -32762))
							(6 (= global178 -32761))
							(7 (= global178 -32759))
							(9 (= global178 -32758))
							(10 (= global178 -32766))
						)
					)
					(switch local5
						(0 (localproc_000c 230 5))
						(1 (localproc_0058 230 6))
						(2 (localproc_000c 230 7))
						(3 (localproc_0058 230 8))
						(4 (localproc_000c 230 9))
						(5 (localproc_0058 230 10))
						(6 (localproc_0058 230 11))
						(7 (localproc_000c 230 12))
						(8 (localproc_0058 230 13))
						(9 (localproc_0058 230 14))
						(else  (localproc_000c 230 15))
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
					(switch local5
						(0 (localproc_000c 230 16))
						(1 (localproc_0058 230 17))
						(5 (localproc_0058 230 18))
						(7 (localproc_000c 230 19))
						(8 (localproc_0058 230 20))
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
	(properties)
	
	(method (doit)
		(super doit:)
		(if (> global172 98) (client setScript: 0))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: 0)
				(= seconds 5)
				(switch gCurRoomNum_4
					(0 (localproc_000c 230 21))
					(1 (localproc_0058 230 22))
					(2 (localproc_000c 230 23))
					(3 (localproc_0058 230 24))
					(4 (localproc_000c 230 25))
					(5 (localproc_000c 230 26))
					(6 (localproc_0058 230 27))
					(7 (localproc_000c 230 28))
					(8 (localproc_000c 230 29))
					(9 (localproc_000c 230 30))
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
					(0 (localproc_0058 230 31))
					(1 (localproc_000c 230 32))
					(2 (localproc_0058 230 33))
					(3 (localproc_000c 230 34))
					(4 (localproc_0058 230 35))
					(5 (localproc_0058 230 36))
					(6 (localproc_000c 230 37))
					(7 (localproc_0058 230 38))
					(8 (localproc_0058 230 39))
					(9 (localproc_0058 230 40))
					(else  (= seconds 1))
				)
			)
			(3
				(cls)
				(lHead cel: 0 forceUpd: stopUpd:)
				(= seconds 5)
				(switch gCurRoomNum_4
					(8 (localproc_000c 230 41))
					(else  (= cycles 1))
				)
			)
			(4
				(= local8 0)
				(if (< gCurRoomNum_4 9) (++ gCurRoomNum_4))
				(cls)
				(User canInput: 1)
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Ethel cel: 0 loop: 1 cycleSpeed: 1 setCycle: End self)
			)
			(1
				(Ethel cel: 0 loop: 2 setCycle: End)
				(= seconds 3)
			)
			(2
				(Ethel loop: 2 setCycle: Beg)
				(if (< (Random 1 100) 40) (= state 0))
				(= seconds (Random 6 12))
			)
			(3
				(Ethel loop: 1 cel: 6 setCycle: Beg)
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Lillian loop: 4 cel: 0 cycleSpeed: 2 setCycle: End)
				(lHead show: stopUpd:)
				(= seconds (Random 6 12))
			)
			(1
				(lHead show: stopUpd:)
				(Lillian loop: 6 setCycle: Fwd)
				(if (< (Random 1 100) 50) (= state 2))
				(= seconds (Random 5 8))
			)
			(2
				(Lillian
					loop: 4
					cel: (- (NumCels Lillian) 1)
					setCycle: Beg
				)
				(if (== local8 70) (= local7 1) (client setScript: 0))
				(= state -1)
				(= seconds (Random 5 8))
			)
			(3
				(lHead hide:)
				(Lillian loop: 2 cycleSpeed: 1 setCycle: End self)
			)
			(4
				(Lillian loop: 3 cycleSpeed: 1 setCycle: Fwd)
				(= seconds 2)
			)
			(5
				(Lillian cel: 2 loop: 2 cycleSpeed: 1 setCycle: Beg)
				(= state 0)
				(= seconds (Random 5 8))
			)
		)
	)
)

(instance comeBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global203 2)
				(Lillian
					view: 500
					posn: -29 148
					setCycle: Walk
					setPri: 10
					setAvoider: ((Avoid new:) offScreenOK: 1)
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
				(= global208 (| global208 $0020))
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
				(DisposeScript 985)
				(lHead setPri: 11 init:)
				(client setScript: talkActions)
			)
		)
	)
)

(instance Ethel of Act
	(properties)
	
	(method (handleEvent event)
		(if
		(< (ego distanceTo: Ethel) (ego distanceTo: Lillian))
			(= global214 8)
		else
			(= global214 32)
		)
		(= theTalker 4)
		(cond 
			(
			(and (MousedOn self event 3) (not (& global207 $0008))) (event claimed: 1) (ParseName {ethel}))
			(
				(and
					(& global207 $0008)
					(or (MousedOn self event 3) (Said 'examine/ethel'))
				)
				(event claimed: 1)
				(Print 230 1)
			)
			((Said 'converse/ethel')
				(switch local0
					(0 (Say 1 230 42))
					(1 (Say 1 230 43))
					(2 (Say 1 230 44))
					(3 (Say 1 230 45))
					(else  (Print 230 46))
				)
				(++ local0)
			)
			((Said 'ask[/ethel]/lil<about')
				(= global212 1)
				(= global209 event)
				(proc243_1 14 230 47)
			)
			((Said 'hear/ethel,lil') (Print 230 48))
			((Said 'examine/drink,glass') (Print 230 49))
			((Said 'examine/deliver') (Print 230 50))
			((Said 'examine/handkerchief') (Print 230 51))
			((Said 'get>')
				(cond 
					((Said '/drink,glass') (Print 230 52))
					((Said '/handkerchief') (Print 230 53))
				)
			)
		)
	)
)

(instance Lillian of Act
	(properties
		y 134
		x 69
		view 500
	)
	
	(method (handleEvent event)
		(if (== global203 2)
			(= theTalker 6)
			(cond 
				(
				(and (MousedOn self event 3) (not (& global207 $0020))) (event claimed: 1) (ParseName {lillian}))
				(
					(and
						(& global207 $0020)
						(or (MousedOn self event 3) (Said 'examine/lil'))
					)
					(event claimed: 1)
					(Print 230 1)
				)
				((Said 'converse/lil')
					(switch local2
						(0 (Say 1 230 54))
						(1 (Say 1 230 55))
						(2 (Say 1 230 56))
						(3 (Say 1 230 57))
						(else  (Say 1 230 58))
					)
					(++ local2)
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
		signal $4000
	)
)
