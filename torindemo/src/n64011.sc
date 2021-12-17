;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64011)
(include sci.sh)
(use Main)
(use Print)

(public
	proc64011_0 0
)

(procedure (proc64011_0 param1 &tmp temp0)
	(switch param1
		(1 (= temp0 (localproc_00ac)))
		(2 (= temp0 (localproc_0229)))
		(3 (= temp0 (localproc_0355)))
		(4 (= temp0 (localproc_0430)))
		(5 (= temp0 (localproc_05ab)))
		(else  (= temp0 8000))
	)
	(DisposeScript -1525 temp0)
)

(procedure (localproc_00ac &tmp temp0)
	(= temp0
		(Print
			y: 50
			fore: 235
			back: 227
			skip: 0
			font: userFont
			addTitle: {The Lands Above}
			addButton: 10000 {Intro} 0 31
			addButton: 11100 {Fahrmans House Int} 0 62
			addButton: 11000 {Fahrmans House Ext} 0 93
			addButton: 10100 {Forest scroller} 0 124
			addButton: 16000 {Crystal City, LS} 0 156
			addButton: 16100 {Crystal City Moat} 0 187
			addButton: 11200 {Sewing basket} 140 31
			addButton: 11300 {Dad's Ashtray} 140 62
			addButton: 12000 {Leaves} 140 93
			addButton: 14000 {Snails} 140 124
			addButton: 13000 {Bog} 200 93
			addButton: 15000 {Guard's House Ext} 240 93
			addButton: 15100 {Guard's House Int} 240 62
			addButton: 15200 {Phenocryst Portal} 240 31
			init:
		)
	)
)

(procedure (localproc_0229 &tmp temp0)
	(= temp0
		(Print
			y: 50
			fore: 235
			back: 227
			skip: 0
			font: userFont
			addTitle: {Escarpa}
			addButton: 20000 {Intro} 0 31
			addButton: 20100 {CliffScroller} 160 31
			addButton: 20200 {Bitternuts} 80 31
			addButton: 20300 {Throne Room} 240 31
			addButton: 20400 {Dragon Cave} 80 62
			addButton: 20500 {Skunk Cave} 240 62
			addButton: 20900 {Vedar} 240 93
			addButton: 20600 {Seraglio} 240 121
			addButton: 20700 {Phace Puzzle} 80 93
			addButton: 20800 {Transporter} 0 93
			init:
		)
	)
)

(procedure (localproc_0355 &tmp temp0)
	(= temp0
		(Print
			y: 50
			fore: 235
			back: 227
			skip: 0
			font: userFont
			addTitle: {Pergola}
			addButton: 30000 {Intro} 0 31
			addButton: 30100 {OpeningScreen} 80 31
			addButton: 30200 {City} 80 62
			addButton: 30300 {Leenah Movie} 160 62
			addButton: 30400 {Star Puzzle} 240 62
			addButton: 30500 {Music Puzzle} 240 31
			init:
		)
	)
)

(procedure (localproc_0430 &tmp temp0)
	(= temp0
		(Print
			y: 50
			fore: 235
			back: 227
			skip: 0
			font: userFont
			addTitle: {Asthenia}
			addButton: -25536 {Intro} 0 31
			addButton: -24636 {Exit} 80 31
			addButton: -22536 {Puzzle} 160 31
			addButton: -24736 {Stones} 0 62
			addButton: -24836 {Mountain} 80 62
			addButton: -24936 {Cannon} 160 62
			addButton: -25036 {Lava} 240 62
			addButton: -25436 {Entrance} 0 93
			addButton: -25136 {SandCU} 80 93
			addButton: -25236 {Spits} 160 93
			addButton: -25336 {Island} 0 124
			addButton: -23336 {Ball Machine} 80 124
			addButton: -23436 {Catapult} 160 124
			addButton: -23536 {Caretakers} 240 124
			init:
		)
	)
)

(procedure (localproc_05ab &tmp temp0)
	(= temp0
		(Print
			y: 50
			fore: 235
			back: 227
			skip: 0
			font: userFont
			addTitle: {Tenebrous}
			addButton: -15536 {Intro} 0 31
			addButton: -15436 {Storeroom} 240 31
			addButton: -15336 {AirDuct1} 160 31
			addButton: -15236 {AirDuctEnd} 80 31
			addButton: -15136 {InPlanter} 80 62
			addButton: -15036 {JailedMovie} 160 62
			addButton: -14936 {Slope} 80 93
			addButton: -14836 {TenebrousLS} 80 124
			addButton: -14736 {TenebrousMS} 160 124
			addButton: -14636 {Backstage} 240 124
			addButton: -12536 {MagicianCU} 80 155
			addButton: -12436 {RabbitCU} 160 155
			addButton: -12336 {ArchivistCU} 240 155
			addButton: -14536 {OnStage} 240 186
			addButton: -10536 {JudgeMovie} 160 186
			addButton: -14436 {NullVoid} 80 186
			addButton: -14336 {Porch} 80 217
			addButton: -14236 {HorrorHall} 160 217
			addButton: -14136 {Lair} 240 217
			init:
		)
	)
)
