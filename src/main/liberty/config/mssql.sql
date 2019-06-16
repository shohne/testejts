
USE [testejts]
GO

/****** Object:  Table [dbo].[tb_cliente]    Script Date: 6/16/2019 4:13:36 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tb_cliente](
	[id_cliente] [int] NOT NULL,
	[nome] [varchar](300) NOT NULL,
	[padrao_consumo] [decimal](20, 2) NULL,
	[data_nascimento] [date] NULL,
	[versao] [int] NULL,
PRIMARY KEY CLUSTERED
(
	[id_cliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO





